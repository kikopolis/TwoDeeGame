package com.kikopolis.entity;

import com.google.inject.Inject;
import com.kikopolis.controls.FacingDirection;

import java.awt.Rectangle;

import static com.kikopolis.config.SpriteConfig.TILE_SIZE;
import static com.kikopolis.config.WindowConfig.GAME_SCREEN_HEIGHT;
import static com.kikopolis.config.WindowConfig.GAME_SCREEN_WIDTH;

public class Player extends AbstractEntity {
    public static final String NAME_ID = "player";
    private int screenX;
    private int screenY;
    private final String[] moveList;
    private final String[] attackList;
    private final String[] guardList;
    
    @Inject
    public Player() {
        super(NAME_ID, 0, 0, new Rectangle());
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = 32;
        hitBox.height = 32;
        screenX = GAME_SCREEN_WIDTH / 2 - TILE_SIZE / 2;
        screenY = GAME_SCREEN_HEIGHT / 2 - TILE_SIZE / 2;
        facingDirection = FacingDirection.DOWN;
        moveList = moveList();
        attackList = attackList();
        guardList = guardList();
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
    
    public String[] getMoveList() {
        return moveList;
    }
    
    public String[] getAttackList() {
        return attackList;
    }
    
    public String[] getGuardList() {
        return guardList;
    }
    
    private String[] moveList() {
        var list = new String[8];
        list[0] = "player_down_1";
        list[1] = "player_down_2";
        list[2] = "player_left_1";
        list[3] = "player_left_2";
        list[4] = "player_right_1";
        list[5] = "player_right_2";
        list[6] = "player_up_1";
        list[7] = "player_up_2";
        return list;
    }
    
    private String[] attackList() {
        return new String[0];
    }
    
    private String[] guardList() {
        return new String[0];
    }
}
