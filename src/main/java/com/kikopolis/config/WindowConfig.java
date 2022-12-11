package com.kikopolis.config;

public final class WindowConfig {
    public static final int GAME_SCREEN_COLUMNS = 16;
    public static final int GAME_SCREEN_ROWS = 12;
    public static final int GAME_SCREEN_WIDTH = GAME_SCREEN_COLUMNS * SpriteConfig.TILE_SIZE;
    public static final int GAME_SCREEN_HEIGHT = GAME_SCREEN_ROWS * SpriteConfig.TILE_SIZE;
    public static final int MAXIMUM_FPS = 60;
    
    private WindowConfig() {}
}
