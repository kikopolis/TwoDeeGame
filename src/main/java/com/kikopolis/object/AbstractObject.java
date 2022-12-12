package com.kikopolis.object;

import com.kikopolis.sprite.Sprite;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class AbstractObject {
    protected static final Logger LOGGER = getLogger(AbstractObject.class);
    protected String name;
    protected Sprite sprite;
    protected boolean collision;
    protected int worldX;
    protected int worldY;
    
    public AbstractObject(
            final String name,
            final Sprite sprite,
            final boolean collision,
            final int worldX,
            final int worldY
                         ) {
        this.name = name;
        this.sprite = sprite;
        this.collision = collision;
        this.worldX = worldX;
        this.worldY = worldY;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }
    
    public boolean isCollision() {
        return collision;
    }
    
    public void setCollision(final boolean collision) {
        this.collision = collision;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public void setWorldX(final int worldX) {
        this.worldX = worldX;
    }
    
    public int getWorldY() {
        return worldY;
    }
    
    public void setWorldY(final int worldY) {
        this.worldY = worldY;
    }
}
