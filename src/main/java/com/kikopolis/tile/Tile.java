package com.kikopolis.tile;

import com.kikopolis.sprite.Sprite;

import java.awt.Rectangle;

import static com.kikopolis.config.SpriteConfig.TILE_SIZE;

public class Tile {
    private final String name;
    private final Sprite sprite;
    private final boolean solid;
    protected Rectangle hitBox;
    
    public Tile(final String name, final Sprite sprite, final boolean solid) {
        this(name, sprite, solid, solid ? new Rectangle(TILE_SIZE, TILE_SIZE) : new Rectangle(0, 0));
    }
    
    public Tile(final String name, final Sprite sprite, final boolean solid, final Rectangle hitBox) {
        this.name = name;
        this.sprite = sprite;
        this.solid = solid;
        this.hitBox = hitBox;
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
    
    public Rectangle getHitBox() {
        return hitBox;
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
