package com.kikopolis;

import com.google.inject.Inject;
import com.kikopolis.entity.Entity;
import com.kikopolis.tile.Tile;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Collision {
    private static final Logger LOGGER = getLogger(Collision.class);
    private final GamePanel gamePanel;
    
    @Inject
    public Collision(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public void checkTileCollision(final Entity entity) {
//        LOGGER.debug("Entity: {}", entity);
        var entityLeftWorldX = entity.getWorldX() + entity.getHitBox().x;
//        LOGGER.debug("Entity left world X: {}", entityLeftWorldX);
        var entityRightWorldX = entityLeftWorldX + entity.getHitBox().width;
//        LOGGER.debug("Entity right world X: {}", entityRightWorldX);
        var entityTopWorldY = entity.getWorldY() + entity.getHitBox().y;
//        LOGGER.debug("Entity top world Y: {}", entityTopWorldY);
        var entityBottomWorldY = entityTopWorldY + entity.getHitBox().height;
//        LOGGER.debug("Entity bottom world Y: {}", entityBottomWorldY);
        var entityLeftColumn = entityLeftWorldX / GamePanel.TILE_SIZE;
//        LOGGER.debug("Entity left column: {}", entityLeftColumn);
        var entityRightColumn = entityRightWorldX / GamePanel.TILE_SIZE;
//        LOGGER.debug("Entity right column: {}", entityRightColumn);
        var entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
//        LOGGER.debug("Entity top row: {}", entityTopRow);
        var entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;
//        LOGGER.debug("Entity bottom row: {}", entityBottomRow);
        Tile tileNum1;
        Tile tileNum2;
        switch (entity.getFacingDirection()) {
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityLeftColumn);
                tileNum2 = gamePanel.getTileManager().getTile(entityTopRow, entityRightColumn);
//                LOGGER.debug("Tile 1: {}", tileNum1);
//                LOGGER.debug("Tile 2: {}", tileNum2);
                if (tileNum1.isSolid()
                    || tileNum2.isSolid()
                    || entityTopRow < 0
                    || entityTopRow >= gamePanel.getTileManager().getMapData().tiles().length) {
                    entity.setWorldY(entity.getWorldY() + entity.getSpeed());
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().getTile(entityBottomRow, entityLeftColumn);
                tileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityRightColumn);
//                LOGGER.debug("Tile 1: {}", tileNum1);
//                LOGGER.debug("Tile 2: {}", tileNum2);
                if (tileNum1.isSolid()
                    || tileNum2.isSolid()
                    || entityBottomRow < 0
                    || entityBottomRow >= gamePanel.getTileManager().getMapData().tiles().length) {
                    entity.setWorldY(entity.getWorldY() - entity.getSpeed());
                }
            }
            case LEFT -> {
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed()) / GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityLeftColumn);
                tileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityLeftColumn);
//                LOGGER.debug("Tile 1: {}", tileNum1);
//                LOGGER.debug("Tile 2: {}", tileNum2);
                if (tileNum1.isSolid()
                    || tileNum2.isSolid()
                    || entityLeftColumn < 0
                    || entityLeftColumn >= gamePanel.getTileManager().getMapData().tiles()[0].length) {
                    entity.setWorldX(entity.getWorldX() + entity.getSpeed());
                }
            }
            case RIGHT -> {
                entityRightColumn = (entityRightWorldX + entity.getSpeed()) / GamePanel.TILE_SIZE;
                tileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityRightColumn);
                tileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityRightColumn);
//                LOGGER.debug("Tile 1: {}", tileNum1);
//                LOGGER.debug("Tile 2: {}", tileNum2);
                if (tileNum1.isSolid()
                    || tileNum2.isSolid()
                    || entityRightColumn < 0
                    || entityRightColumn >= gamePanel.getTileManager().getMapData().tiles()[0].length) {
                    entity.setWorldX(entity.getWorldX() - entity.getSpeed());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + entity.getFacingDirection());
        }
    }
}
