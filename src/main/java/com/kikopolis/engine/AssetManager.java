package com.kikopolis.engine;

import com.kikopolis.entity.AbstractEntity;
import com.kikopolis.object.AbstractObject;
import com.kikopolis.sprite.Sprite;
import com.kikopolis.tile.Tile;

public interface AssetManager {
    void loadSprites();
    Sprite getSpriteByName(final String name);
    void loadEntities();
    AbstractEntity getEntityByName(final String name);
    void loadObjects();
    AbstractObject getObjectByName(final String name);
    void loadTiles();
    Tile getTile(final String name);
}
