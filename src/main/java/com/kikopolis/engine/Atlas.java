package com.kikopolis.engine;

import com.google.inject.Inject;
import com.kikopolis.controls.FacingDirection;
import com.kikopolis.controls.KeyHandler;
import com.kikopolis.controls.KeyboardControlsList;
import com.kikopolis.entity.Player;
import com.kikopolis.map.AbstractMap;
import com.kikopolis.map.MapManager;
import com.kikopolis.tile.Tile;
import org.slf4j.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import static com.kikopolis.config.SpriteConfig.TILE_SIZE;
import static com.kikopolis.config.WindowConfig.GAME_SCREEN_HEIGHT;
import static com.kikopolis.config.WindowConfig.GAME_SCREEN_WIDTH;
import static com.kikopolis.config.WindowConfig.MAXIMUM_FPS;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Atlas, from Greek meaning "A Titan", is the main game engine.
 */
public final class Atlas extends JPanel implements Runnable {
    // todo this is the main game engine
    // todo this needs a collison/physics engine
    // todo this needs a renderer to render 2d graphics
    private static final Logger LOGGER = getLogger(Atlas.class);
    private final transient AssetManager assetManager;
    private final transient Physics physics;
    private final transient KeyHandler keyHandler;
    private final transient MapManager mapManager;
    private transient AbstractMap currentMap;
    private transient int currentMapIndex;
    private transient Thread gameThread;
    
    @Inject
    public Atlas(
            final AssetManager assetManager,
            final Physics physics,
            final KeyHandler keyHandler,
            final MapManager mapManager
                ) {
        currentMapIndex = 0;
        this.assetManager = assetManager;
        this.physics = physics;
        this.keyHandler = keyHandler;
        this.mapManager = mapManager;
        
        // Load all assets to memory
        // first need to load sprites
        this.assetManager.loadSprites();
        // then load entities
        this.assetManager.loadEntities();
        // then load objects
        this.assetManager.loadObjects();
        // then load tiles
        this.assetManager.loadTiles();
        // then load maps
        this.mapManager.loadMaps();
        
        // set the map
//        this.currentMap = this.mapManager.getMap(this.mapManager.getMapProgression()[currentMapIndex]);
        this.currentMap = this.mapManager.getMap("test");
        
        // create the game window and the panel inside it
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Kikopolis TwoDee Adventure");
        setPreferredSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(this.keyHandler);
        setFocusable(true);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public void startUp() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
            LOGGER.debug("Game thread started");
        }
    }
    
    @Override
    public void run() {
        final var drawInterval = 1_000_000_000 / MAXIMUM_FPS;
        var lastTime = System.nanoTime();
        var delta = 0.0D;
        var timer = 0L;
        var drawCount = 0;
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += ((double) currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            while (delta >= 1) {
                update();
                drawCount++;
                delta--;
                drawCount++;
            }
            repaint();
            if (timer >= 1_000_000_000) {
                LOGGER.debug("FPS: {}", drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        drawMap((Graphics2D) graphics);
        drawPlayer((Graphics2D) graphics);
        
        graphics.dispose();
    }
    
    private void update() {
        // calculate movement
        movePlayer(this.keyHandler.getPressedKeys());
        // calculate collisions
        checkCollision();
        // calculate physics
    }
    
    public void movePlayer(final List<Integer> keyCodes) {
        final var player = this.assetManager.getEntityByName(Player.NAME_ID);
        for (final var keyCode : keyCodes) {
            if (KeyboardControlsList.MOVE_UP.contains(keyCode)) {
                player.setFacingDirection(FacingDirection.UP);
                player.setSpriteCounter(player.getSpriteCounter() + 1);
                player.setWorldY(player.getWorldY() - player.getSpeed());
            } else if (KeyboardControlsList.MOVE_DOWN.contains(keyCode)) {
                player.setFacingDirection(FacingDirection.DOWN);
                player.setSpriteCounter(player.getSpriteCounter() + 1);
                player.setWorldY(player.getWorldY() + player.getSpeed());
            } else if (KeyboardControlsList.MOVE_LEFT.contains(keyCode)) {
                player.setFacingDirection(FacingDirection.LEFT);
                player.setSpriteCounter(player.getSpriteCounter() + 1);
                player.setWorldX(player.getWorldX() - player.getSpeed());
            } else if (KeyboardControlsList.MOVE_RIGHT.contains(keyCode)) {
                player.setFacingDirection(FacingDirection.RIGHT);
                player.setSpriteCounter(player.getSpriteCounter() + 1);
                player.setWorldX(player.getWorldX() + player.getSpeed());
            }
        }
    }
    
    public void drawPlayer(final Graphics2D graphics) {
        final var player = (Player) this.assetManager.getEntityByName(Player.NAME_ID);
        BufferedImage image = switch (player.getFacingDirection()) {
            case UP -> player.getSpriteNumber() == 1 ? player.getUp1().getImage() : player.getUp2().getImage();
            case DOWN -> player.getSpriteNumber() == 1 ? player.getDown1().getImage() : player.getDown2().getImage();
            case LEFT -> player.getSpriteNumber() == 1 ? player.getLeft1().getImage() : player.getLeft2().getImage();
            case RIGHT -> player.getSpriteNumber() == 1 ? player.getRight1().getImage() : player.getRight2().getImage();
        };
        if (player.getSpriteCounter() > 12) {
            switch (player.getSpriteNumber()) {
                case 1 -> player.setSpriteNumber(2);
                case 2 -> player.setSpriteNumber(1);
                default -> LOGGER.error("Invalid sprite number: {}", player.getSpriteNumber());
            }
            player.setSpriteCounter(0);
        }
        graphics.drawImage(
                image,
                player.getScreenX(),
                player.getScreenY(),
                player.getWidth(),
                player.getHeight(),
                null
                          );
    }
    
    private void drawMap(final Graphics2D graphics) {
        final var player = (Player) this.assetManager.getEntityByName(Player.NAME_ID);
        final var mapTiles = currentMap.getTileData();
        var worldColumn = 0;
        var worldRow = 0;
        while (worldRow < currentMap.getRows() && worldColumn < currentMap.getColumns()) {
            final var tile = assetManager.getTile(mapTiles[worldRow][worldColumn]);
            if (tile == null) {
                LOGGER.error("Tile not found: {}", mapTiles[worldRow][worldColumn]);
            } else {
                int worldX = worldColumn * TILE_SIZE;
                int worldY = worldRow * TILE_SIZE;
                int screenX = worldX - player.getWorldX() + player.getScreenX();
                int screenY = worldY - player.getWorldY() + player.getScreenY();
                if (worldX > player.getWorldX() - player.getScreenX() - TILE_SIZE
                    && worldX < player.getWorldX() + player.getScreenX() + TILE_SIZE
                    && worldY > player.getWorldY() - player.getScreenY() - TILE_SIZE
                    && worldY < player.getWorldY() + player.getScreenY() + TILE_SIZE) {
                    var sprite = tile.getSprite();
                    graphics.drawImage(
                            sprite.getImage(),
                            screenX,
                            screenY,
                            TILE_SIZE,
                            TILE_SIZE,
                            null
                                      );
                }
            }
            worldColumn++;
            if (worldColumn >= currentMap.getColumns()) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
    
    private void checkCollision() {
        checkPlayerCollision();
    }
    
    private void checkPlayerCollision() {
        final var player = (Player) this.assetManager.getEntityByName(Player.NAME_ID);
        var playerWorldLeftX = player.getWorldX() + player.getHitBox().x;
        var playerWorldRightX = playerWorldLeftX + player.getHitBox().width;
        var playerWorldTopY = player.getWorldY() + player.getHitBox().y;
        var playerWorldBottomY = playerWorldTopY + player.getHitBox().height;
        var playerLeftColumn = playerWorldLeftX / TILE_SIZE;
        var playerRightColumn = playerWorldRightX / TILE_SIZE;
        var playerTopRow = playerWorldTopY / TILE_SIZE;
        var playerBottomRow = playerWorldBottomY / TILE_SIZE;
        // to facilitate diagonal collision, we will drop the facing direction and instead check all 4 directions
        Tile tileUpLeft = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerLeftColumn));
        Tile tileUpRight = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerRightColumn));
        Tile tileDiagonalUpLeft = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerLeftColumn - 1));
        Tile tileDiagonalUpRight = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerRightColumn + 1));
        Tile tileDownLeft = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerLeftColumn));
        Tile tileDownRight = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerRightColumn));
        Tile tileDiagonalDownLeft = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerLeftColumn - 1));
        Tile tileDiagonalDownRight = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerRightColumn + 1));
        Tile tileLeftUpper = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerLeftColumn));
        Tile tileLeftLower = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerLeftColumn));
        Tile tileRightUpper = assetManager.getTile(
                currentMap.getTileName(playerTopRow, playerRightColumn));
        Tile tileRightLower = assetManager.getTile(
                currentMap.getTileName(playerBottomRow, playerRightColumn));
    
        if (isPlayerCollidingWithLeftMapEdge(player)) {
            player.setWorldX(-player.getHitBox().x);
        }
        if (isPlayerCollidingWithRightMapEdge(player)) {
            player.setWorldX(currentMap.getColumns() * TILE_SIZE - player.getHitBox().x - player.getHitBox().width);
        }
        if (isPlayerCollidingWithTopMapEdge(player)) {
            player.setWorldY(-player.getHitBox().y);
        }
        if (isPlayerCollidingWithBottomMapEdge(player)) {
            player.setWorldY(currentMap.getRows() * TILE_SIZE - player.getHitBox().y - player.getHitBox().height);
        }
        
        if (player.getHitBox().intersects(tileUpLeft.getHitBox())
            || player.getHitBox().intersects(tileUpRight.getHitBox())) {
            player.setWorldY(player.getWorldY() + player.getSpeed());
        }
        if (player.getHitBox().intersects(tileDownLeft.getHitBox())
            || player.getHitBox().intersects(tileDownRight.getHitBox())) {
            player.setWorldY(player.getWorldY() - player.getSpeed());
        }
        if (player.getHitBox().intersects(tileLeftUpper.getHitBox())
            || player.getHitBox().intersects(tileLeftLower.getHitBox())) {
            player.setWorldX(player.getWorldX() + player.getSpeed());
        }
        if (player.getHitBox().intersects(tileRightUpper.getHitBox())
            || player.getHitBox().intersects(tileRightLower.getHitBox())) {
            player.setWorldX(player.getWorldX() - player.getSpeed());
        }
        if (player.getHitBox().intersects(tileDiagonalUpLeft.getHitBox())
            || player.getHitBox().intersects(tileDiagonalUpRight.getHitBox())) {
            player.setWorldY(player.getWorldY() + player.getSpeed());
            player.setWorldX(player.getWorldX() + player.getSpeed());
        }
        if (player.getHitBox().intersects(tileDiagonalDownLeft.getHitBox())
            || player.getHitBox().intersects(tileDiagonalDownRight.getHitBox())) {
            player.setWorldY(player.getWorldY() - player.getSpeed());
            player.setWorldX(player.getWorldX() - player.getSpeed());
        }
    }
    
    private boolean isPlayerCollidingWithLeftMapEdge(final Player player) {
        return player.getWorldX() + player.getHitBox().x < 0;
    }
    
    private boolean isPlayerCollidingWithRightMapEdge(final Player player) {
        return player.getWorldX() + player.getHitBox().x + player.getHitBox().width
               > currentMap.getColumns() * TILE_SIZE;
    }
    
    private boolean isPlayerCollidingWithTopMapEdge(final Player player) {
        return player.getWorldY() + player.getHitBox().y < 0;
    }
    
    private boolean isPlayerCollidingWithBottomMapEdge(final Player player) {
        return player.getWorldY() + player.getHitBox().y + player.getHitBox().height
               > currentMap.getRows() * TILE_SIZE;
    }
}
