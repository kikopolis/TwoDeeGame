package com.kikopolis;

import com.google.inject.Guice;
import com.kikopolis.core.GameModule;
import com.kikopolis.engine.Atlas;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger LOGGER = getLogger(Main.class);
    
    public static void main(String[] args) {
        // set logging level to lowest possible
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
        try {
            var injector = Guice.createInjector(new GameModule());
            var engine = injector.getInstance(Atlas.class);
            engine.startUp();
        } catch (Exception e) {
            LOGGER.error("Error starting game", e);
            System.exit(1);
        }
    }
}