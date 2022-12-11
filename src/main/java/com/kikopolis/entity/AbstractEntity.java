package com.kikopolis.entity;

import com.kikopolis.config.SpriteConfig;
import com.kikopolis.controls.FacingDirection;
import com.kikopolis.sprite.Sprite;

import java.awt.Rectangle;

public abstract class AbstractEntity {
    protected String name;
    protected int width;
    protected int height;
    protected int worldY;
    protected int worldX;
    protected int speed;
    protected Sprite up1;
    protected Sprite up2;
    protected Sprite down1;
    protected Sprite down2;
    protected Sprite left1;
    protected Sprite left2;
    protected Sprite right1;
    protected Sprite right2;
    protected FacingDirection facingDirection;
    protected int spriteCounter;
    protected int spriteNumber;
    protected Rectangle hitBox;
    
    protected AbstractEntity(final String name, final int worldX, final int worldY, final Rectangle hitBox) {
        this.name = name;
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = SpriteConfig.TILE_SIZE;
        this.height = SpriteConfig.TILE_SIZE;
        this.speed = 4;
        spriteCounter = 0;
        spriteNumber = 1;
        this.hitBox = hitBox;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
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
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    public Sprite getUp1() {
        return up1;
    }
    
    public void setUp1(final Sprite up1) {
        this.up1 = up1;
    }
    
    public Sprite getUp2() {
        return up2;
    }
    
    public void setUp2(final Sprite up2) {
        this.up2 = up2;
    }
    
    public Sprite getDown1() {
        return down1;
    }
    
    public void setDown1(final Sprite down1) {
        this.down1 = down1;
    }
    
    public Sprite getDown2() {
        return down2;
    }
    
    public void setDown2(final Sprite down2) {
        this.down2 = down2;
    }
    
    public Sprite getLeft1() {
        return left1;
    }
    
    public void setLeft1(final Sprite left1) {
        this.left1 = left1;
    }
    
    public Sprite getLeft2() {
        return left2;
    }
    
    public void setLeft2(final Sprite left2) {
        this.left2 = left2;
    }
    
    public Sprite getRight1() {
        return right1;
    }
    
    public void setRight1(final Sprite right1) {
        this.right1 = right1;
    }
    
    public Sprite getRight2() {
        return right2;
    }
    
    public void setRight2(final Sprite right2) {
        this.right2 = right2;
    }
    
    public FacingDirection getFacingDirection() {
        return facingDirection;
    }
    
    public void setFacingDirection(final FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
    }
    
    public int getSpriteCounter() {
        return spriteCounter;
    }
    
    public void setSpriteCounter(final int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }
    
    public int getSpriteNumber() {
        return spriteNumber;
    }
    
    public void setSpriteNumber(final int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }
    
    public Rectangle getHitBox() {
        return hitBox;
    }
    
    public void setHitBox(final Rectangle hitBox) {
        this.hitBox = hitBox;
    }
}
