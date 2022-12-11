package com.kikopolis.tile;

import com.kikopolis.sprite.Sprite;

public class Tile {
    private final String name;
    private final Sprite sprite;
    private final boolean solid;
    
    public Tile(final String name, final Sprite sprite, final boolean solid) {
        this.name = name;
        this.sprite = sprite;
        this.solid = solid;
    }
    
    public String getName() {
        return name;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    @Override
    public String toString() {
        return "Tile{" +
               "name='" + name + '\'' +
               ", solid=" + solid +
               ", sprite=" + sprite +
               '}';
    }
}
