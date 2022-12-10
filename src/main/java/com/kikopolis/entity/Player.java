package com.kikopolis.entity;

import com.google.inject.Inject;
import com.kikopolis.controls.FacingDirection;
import com.kikopolis.controls.KeyboardControlsList;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.kikopolis.GamePanel.GAME_SCREEN_HEIGHT;
import static com.kikopolis.GamePanel.GAME_SCREEN_WIDTH;
import static com.kikopolis.GamePanel.TILE_SIZE;
import static org.slf4j.LoggerFactory.getLogger;

public final class Player extends Entity {
    private static final Logger LOGGER = getLogger(Player.class);
    private int screenX;
    private int screenY;
    
    @Inject
    public Player() {
        super(0, 0, TILE_SIZE, TILE_SIZE, 5, new Rectangle());
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = 32;
        hitBox.height = 32;
        this.screenX = GAME_SCREEN_WIDTH / 2 - TILE_SIZE / 2;
        this.screenY = GAME_SCREEN_HEIGHT / 2 - TILE_SIZE / 2;
        facingDirection = FacingDirection.DOWN;
        loadImages();
    }
    
    public int getScreenX() {
        return screenX;
    }
    
    public void setScreenX(final int screenX) {
        this.screenX = screenX;
    }
    
    public int getScreenY() {
        return screenY;
    }
    
    public void setScreenY(final int screenY) {
        this.screenY = screenY;
    }
    
    public void update(final List<Integer> keyCodes) {
        move(keyCodes);
        if (spriteCounter > 12) {
            switch (spriteNumber) {
                case 1 -> spriteNumber = 2;
                case 2 -> spriteNumber = 1;
                default -> LOGGER.error("Invalid sprite number: {}", spriteNumber);
            }
            spriteCounter = 0;
        }
    }
    
    @Override
    public void draw(final Graphics2D g) {
        BufferedImage image = switch (facingDirection) {
            case UP -> spriteNumber == 1 ? up1 : up2;
            case DOWN -> spriteNumber == 1 ? down1 : down2;
            case LEFT -> spriteNumber == 1 ? left1 : left2;
            case RIGHT -> spriteNumber == 1 ? right1 : right2;
        };
        g.drawImage(image, screenX, screenY, width, height, null);
        g.dispose();
    }
    
    private void move(final List<Integer> keyCodes) {
        for (final var keyCode : keyCodes) {
            if (KeyboardControlsList.MOVE_UP.contains(keyCode)) {
                facingDirection = FacingDirection.UP;
                spriteCounter++;
                worldY -= speed;
            } else if (KeyboardControlsList.MOVE_DOWN.contains(keyCode)) {
                facingDirection = FacingDirection.DOWN;
                spriteCounter++;
                worldY += speed;
            } else if (KeyboardControlsList.MOVE_LEFT.contains(keyCode)) {
                facingDirection = FacingDirection.LEFT;
                spriteCounter++;
                worldX -= speed;
            } else if (KeyboardControlsList.MOVE_RIGHT.contains(keyCode)) {
                facingDirection = FacingDirection.RIGHT;
                spriteCounter++;
                worldX += speed;
            }
        }
    }
    
    private void loadImages() {
        up1 = getImage("/sprites/player/walking_sprites/boy_up_1.png");
        up2 = getImage("/sprites/player/walking_sprites/boy_up_2.png");
        down1 = getImage("/sprites/player/walking_sprites/boy_down_1.png");
        down2 = getImage("/sprites/player/walking_sprites/boy_down_2.png");
        left1 = getImage("/sprites/player/walking_sprites/boy_left_1.png");
        left2 = getImage("/sprites/player/walking_sprites/boy_left_2.png");
        right1 = getImage("/sprites/player/walking_sprites/boy_right_1.png");
        right2 = getImage("/sprites/player/walking_sprites/boy_right_2.png");
    }
    
    private BufferedImage getImage(final String imageResource) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(imageResource));
        } catch (Exception e) {
            LOGGER.debug("Error loading image: {}", imageResource);
        }
        return image;
    }
    
    @Override
    public String toString() {
        return "Player{" +
               "screenX=" + screenX +
               ", screenY=" + screenY +
               ", width=" + width +
               ", height=" + height +
               ", worldY=" + worldY +
               ", worldX=" + worldX +
               ", speed=" + speed +
               ", hitBox=" + hitBox +
               '}';
    }
}
