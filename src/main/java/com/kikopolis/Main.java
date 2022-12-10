package com.kikopolis;

import com.google.inject.Guice;
import com.kikopolis.core.GameModule;
import org.slf4j.Logger;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import static org.slf4j.LoggerFactory.getLogger;

public class Main {
    private static final Logger LOGGER = getLogger(Main.class);
    
    public static void main(String[] args) {
        // set default logging level to lowest possible
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
        try {
            var window = new JFrame();
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Kikopolis TwoDee Adventure");
            
            var injector = Guice.createInjector(new GameModule());
            
            var gamePanel = injector.getInstance(GamePanel.class);
            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.startGameThread();
        } catch (Exception e) {
            LOGGER.error("Error starting game", e);
            e.printStackTrace();
        }
    }
}