package com.kikopolis;

import com.google.inject.Inject;
import com.kikopolis.controls.KeyHandler;
import com.kikopolis.entity.Player;
import com.kikopolis.tile.TileManager;
import org.slf4j.Logger;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static org.slf4j.LoggerFactory.getLogger;

public class GamePanel extends JPanel implements Runnable {
    private static final Logger LOGGER = getLogger(GamePanel.class);
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int GAME_SCREEN_COLUMNS = 16;
    public static final int GAME_SCREEN_ROWS = 12;
    public static final int GAME_SCREEN_WIDTH = GAME_SCREEN_COLUMNS * TILE_SIZE;
    public static final int GAME_SCREEN_HEIGHT = GAME_SCREEN_ROWS * TILE_SIZE;
    public static final int MAXIMUM_FPS = 60;
    private final transient Player player;
    private final transient TileManager tileManager;
    private final transient KeyHandler keyHandler;
    private final int worldColumns;
    private final int worldRows;
    private final int worldWidth;
    private final int worldHeight;
    private final Collision collision;
    private transient Thread gameThread;
    
    @Inject
    public GamePanel(final KeyHandler keyHandler, final Player player, final TileManager tileManager) {
        this.keyHandler = keyHandler;
        this.player = player;
        this.tileManager = tileManager;
        this.collision = new Collision(this);
        
        var mapData = tileManager.getMapData();
        
        worldColumns = mapData.rows();
        worldRows = mapData.columns();
        worldWidth = worldColumns * TILE_SIZE;
        worldHeight = worldRows * TILE_SIZE;
        
        this.player.setWorldX(tileManager.getMapData().startX());
        this.player.setWorldY(tileManager.getMapData().startY());
        
        setPreferredSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(this.keyHandler);
        setFocusable(true);
    }
    
    public TileManager getTileManager() {
        return tileManager;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void startGameThread() {
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
    
    public void update() {
        collision.checkTileCollision(player);
        player.update(keyHandler.getPressed());
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        tileManager.draw((Graphics2D) g);
        player.draw((Graphics2D) g);
    }
}
