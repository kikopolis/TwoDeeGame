package com.kikopolis.engine;

import com.kikopolis.entity.AbstractEntity;
import com.kikopolis.tile.Tile;

public interface Physics {
    boolean isEntityCollidingWithTile(final AbstractEntity entity, final Tile tile);
    boolean isEntityCollidingWithEntity(final AbstractEntity entity1, final AbstractEntity entity2);
    boolean isEntityCollidingWithObject(final AbstractEntity entity, final Object object);
}
