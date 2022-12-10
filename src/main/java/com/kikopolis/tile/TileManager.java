package com.kikopolis.tile;

import com.google.inject.Inject;
import com.kikopolis.GamePanel;
import com.kikopolis.entity.Player;
import com.kikopolis.map.MapData;
import com.kikopolis.map.WorldMap;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Arrays;

import static com.kikopolis.GamePanel.TILE_SIZE;
import static org.slf4j.LoggerFactory.getLogger;

public class TileManager {
    private static final Logger LOGGER = getLogger(TileManager.class);
    private int tileSetFileCount;
    private int collisionTileSetFileCount;
    private int nonCollisionTileSetFileCount;
    private File collisionTilesDir;
    private File nonCollisionTilesDir;
    private final Tile[] tileSet;
    private Tile[][] mapTiles;
    private String[][] mapTileData;
    private Player player;
    
    @Inject
    public TileManager(final Player player) {
        this.player = player;
        tileSetFileCount = 0;
        try {
            collisionTilesDir = new File(getClass().getResource("/tiles/collision_tiles").toURI());
            nonCollisionTilesDir = new File(getClass().getResource("/tiles/non_collision_tiles").toURI());
        } catch (URISyntaxException e) {
            LOGGER.error("Error getting collision tiles directory: {}", e.getMessage());
        }
        try (var collisionTilesStream = Files.list(collisionTilesDir.toPath())) {
            collisionTileSetFileCount = (int) collisionTilesStream.count();
            tileSetFileCount += collisionTileSetFileCount;
        } catch (IOException e) {
            LOGGER.error("Error getting collision tiles directory: {}", e.getMessage());
        }
        try (var nonCollisionTilesStream = Files.list(nonCollisionTilesDir.toPath())) {
            nonCollisionTileSetFileCount = (int) nonCollisionTilesStream.count();
            tileSetFileCount += nonCollisionTileSetFileCount;
        } catch (IOException e) {
            LOGGER.error("Error getting non-collision tiles directory: {}", e.getMessage());
        }
        tileSet = new Tile[tileSetFileCount];
        mapTiles = new Tile[getMapData().rows()][getMapData().columns()];
        loadTileSet();
        loadMap();
    }
    
    private void loadTileSet() {
        try {
            final var tilesWithCollision = collisionTilesDir.listFiles();
            final var tilesWithoutCollision = nonCollisionTilesDir.listFiles();
            for (int i = 0; i < collisionTileSetFileCount; i++) {
                var name = tilesWithCollision[i].getName();
                var nameWithoutExtension = name.substring(0, name.lastIndexOf('.'));
                var tile = new Tile(nameWithoutExtension, ImageIO.read(tilesWithCollision[i]), true);
                tileSet[i] = tile;
            }
            for (int i = 0; i < nonCollisionTileSetFileCount; i++) {
                var name = tilesWithoutCollision[i].getName();
                var nameWithoutExtension = name.substring(0, name.lastIndexOf('.'));
                var tile = new Tile(nameWithoutExtension, ImageIO.read(tilesWithoutCollision[i]), false);
                tileSet[collisionTileSetFileCount + i] = tile;
            }
        } catch (Exception e) {
            LOGGER.error("Error loading tiles", e);
        }
        LOGGER.debug("Tile set loaded");
        LOGGER.debug("Tile set size: {}", tileSetFileCount);
    }
    
    private void loadMap() {
        final var mapData = getMapData();
        mapTileData = mapData.tiles();
        for (var row = 0; row < mapData.rows(); row++) {
            for (var column = 0; column < mapData.columns(); column++) {
                // get the key by the tile name
                final var tileNameOnMap = mapTileData[row][column];
                var tile = Arrays.stream(tileSet)
                                 .filter(t -> t.getName().equals(tileNameOnMap))
                                 .findFirst()
                                 .orElse(null);
                if (tile == null) {
                    LOGGER.error("Tile not found: {}", tileNameOnMap);
                    throw new RuntimeException("Tile not found: " + tileNameOnMap);
                }
                mapTiles[row][column] = tile;
            }
        }
    }
    
    public void draw(final Graphics2D g) {
        final var mapData = getMapData();
        var worldColumn = 0;
        var worldRow = 0;
        while (worldRow < mapData.rows() && worldColumn < mapData.columns()) {
            final var tile = mapTiles[worldRow][worldColumn];
            int worldX = worldColumn * TILE_SIZE;
            int worldY = worldRow * TILE_SIZE;
            int screenX = worldX - player.getWorldX() + player.getScreenX();
            int screenY = worldY - player.getWorldY() + player.getScreenY();
            if (worldX > player.getWorldX() - player.getScreenX() - TILE_SIZE
                && worldX < player.getWorldX() + player.getScreenX() + TILE_SIZE
                && worldY > player.getWorldY() - player.getScreenY() - TILE_SIZE
                && worldY < player.getWorldY() + player.getScreenY() + TILE_SIZE) {
                g.drawImage(tile.getImage(), screenX, screenY, TILE_SIZE, TILE_SIZE, null);
            }
            worldColumn++;
            if (worldColumn == mapData.columns()) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
    
    public MapData getMapData() {
        return new MapData(
                WorldMap.getMapData().length,
                WorldMap.getMapData()[0].length,
                0,
                0,
                100,
                100,
                WorldMap.getMapData()
        );
    }
    
    public Tile getTile(final int row, final int column) {
        if (row < 0 || row >= mapTiles.length || column < 0 || column >= mapTiles[0].length) {
            return new Tile("null", null, true);
        }
        return mapTiles[row][column];
    }
}
