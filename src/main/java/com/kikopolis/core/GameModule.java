package com.kikopolis.core;

import com.google.inject.AbstractModule;
import com.kikopolis.controls.KeyHandler;
import com.kikopolis.engine.Archimedes;
import com.kikopolis.engine.AssetManager;
import com.kikopolis.engine.Atlas;
import com.kikopolis.engine.Bertram;
import com.kikopolis.engine.Physics;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Atlas.class).asEagerSingleton();
        bind(AssetManager.class).to(Bertram.class).asEagerSingleton();
        bind(Physics.class).to(Archimedes.class).asEagerSingleton();
        
        bind(KeyHandler.class).asEagerSingleton();
    }
}
