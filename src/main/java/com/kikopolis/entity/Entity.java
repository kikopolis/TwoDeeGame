package com.kikopolis.entity;

import com.kikopolis.controls.FacingDirection;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final int width;
    protected final int height;
    protected int worldY;
    protected int worldX;
    protected int speed;
    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;
    protected FacingDirection facingDirection;
    protected int spriteCounter;
    protected int spriteNumber;
    protected Rectangle hitBox;
    
    protected Entity(
            final int worldX,
            final int worldY,
            final int width,
            final int height,
            final int speed,
            final Rectangle hitBox
                    ) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.speed = speed;
        spriteCounter = 0;
        spriteNumber = 1;
        this.hitBox = hitBox;
    }

//    public abstract void update();
    
    public abstract void draw(final Graphics2D g);
    
    public int getWorldY() {
        return worldY;
    }
    
    public void setWorldY(final int worldY) {
        this.worldY = worldY;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public void setWorldX(final int worldX) {
        this.worldX = worldX;
    }
    
    public Rectangle getHitBox() {
        return hitBox;
    }
    
    public void setHitBox(final Rectangle hitBox) {
        this.hitBox = hitBox;
    }
    
    public FacingDirection getFacingDirection() {
        return facingDirection;
    }
    
    public int getSpeed() {
        return speed;
    }
}
