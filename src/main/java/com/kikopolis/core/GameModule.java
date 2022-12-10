package com.kikopolis.core;

import com.google.inject.AbstractModule;
import com.kikopolis.controls.KeyHandler;
import com.kikopolis.entity.Player;
import com.kikopolis.tile.TileManager;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(KeyHandler.class).asEagerSingleton();
        bind(TileManager.class).asEagerSingleton();
        bind(Player.class).asEagerSingleton();
    }
}
