package com.kikopolis.engine;

import com.google.inject.Inject;
import com.kikopolis.entity.AbstractEntity;
import com.kikopolis.tile.Tile;

/**
 * Archimedes, an ancient Greek mathematician, is the physics and collision engine.
 */
public final class Archimedes implements Physics {
    @Inject
    public Archimedes() {}
    
    @Override
    public boolean isEntityCollidingWithTile(final AbstractEntity entity, final Tile tile) {
        return false;
    }
    
    @Override
    public boolean isEntityCollidingWithEntity(final AbstractEntity entity1, final AbstractEntity entity2) {
        return false;
    }
    
    @Override
    public boolean isEntityCollidingWithObject(final AbstractEntity entity, final Object object) {
        return false;
    }
}
