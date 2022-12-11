package com.kikopolis;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class Collision {
    private static final Logger LOGGER = getLogger(Collision.class);
    
//    public void checkTileCollision(final AbstractEntity abstractEntity) {
//        var entityLeftWorldX = abstractEntity.getWorldX() + abstractEntity.getHitBox().x;
//        var entityRightWorldX = entityLeftWorldX + abstractEntity.getHitBox().width;
//        var entityTopWorldY = abstractEntity.getWorldY() + abstractEntity.getHitBox().y;
//        var entityBottomWorldY = entityTopWorldY + abstractEntity.getHitBox().height;
//        var entityLeftColumn = entityLeftWorldX / GamePanel.TILE_SIZE;
//        var entityRightColumn = entityRightWorldX / GamePanel.TILE_SIZE;
//        var entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
//        var entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;
//        AbstractTile abstractTileNum1;
//        AbstractTile abstractTileNum2;
//        switch (abstractEntity.getFacingDirection()) {
//            case UP -> {
//                entityTopRow = (entityTopWorldY - abstractEntity.getSpeed()) / GamePanel.TILE_SIZE;
//                abstractTileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityLeftColumn);
//                abstractTileNum2 = gamePanel.getTileManager().getTile(entityTopRow, entityRightColumn);
//                if (abstractTileNum1.isSolid()
//                    || abstractTileNum2.isSolid()
//                    || entityTopRow < 0
//                    || entityTopRow >= gamePanel.getTileManager().getMapData().tiles().length) {
//                    abstractEntity.setWorldY(abstractEntity.getWorldY() + abstractEntity.getSpeed());
//                }
//            }
//            case DOWN -> {
//                entityBottomRow = (entityBottomWorldY + abstractEntity.getSpeed()) / GamePanel.TILE_SIZE;
//                abstractTileNum1 = gamePanel.getTileManager().getTile(entityBottomRow, entityLeftColumn);
//                abstractTileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityRightColumn);
//                if (abstractTileNum1.isSolid()
//                    || abstractTileNum2.isSolid()
//                    || entityBottomRow < 0
//                    || entityBottomRow >= gamePanel.getTileManager().getMapData().tiles().length) {
//                    abstractEntity.setWorldY(abstractEntity.getWorldY() - abstractEntity.getSpeed());
//                }
//            }
//            case LEFT -> {
//                entityLeftColumn = (entityLeftWorldX - abstractEntity.getSpeed()) / GamePanel.TILE_SIZE;
//                abstractTileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityLeftColumn);
//                abstractTileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityLeftColumn);
//                if (abstractTileNum1.isSolid()
//                    || abstractTileNum2.isSolid()
//                    || entityLeftColumn < 0
//                    || entityLeftColumn >= gamePanel.getTileManager().getMapData().tiles()[0].length) {
//                    abstractEntity.setWorldX(abstractEntity.getWorldX() + abstractEntity.getSpeed());
//                }
//            }
//            case RIGHT -> {
//                entityRightColumn = (entityRightWorldX + abstractEntity.getSpeed()) / GamePanel.TILE_SIZE;
//                abstractTileNum1 = gamePanel.getTileManager().getTile(entityTopRow, entityRightColumn);
//                abstractTileNum2 = gamePanel.getTileManager().getTile(entityBottomRow, entityRightColumn);
//                if (abstractTileNum1.isSolid()
//                    || abstractTileNum2.isSolid()
//                    || entityRightColumn < 0
//                    || entityRightColumn >= gamePanel.getTileManager().getMapData().tiles()[0].length) {
//                    abstractEntity.setWorldX(abstractEntity.getWorldX() - abstractEntity.getSpeed());
//                }
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + abstractEntity.getFacingDirection());
//        }
//    }
}
