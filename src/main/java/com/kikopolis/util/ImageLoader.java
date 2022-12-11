package com.kikopolis.util;

import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public final class ImageLoader {
    private static final Logger LOGGER = getLogger(ImageLoader.class);
    
    private ImageLoader() {}
    
    public static BufferedImage loadImageResource(final String resourcePath) {
        try {
            var image = ImageLoader.class.getResource(resourcePath);
            return ImageIO.read(image);
        } catch (IOException e) {
            LOGGER.error("Error loading image resource", e);
        }
        return null;
    }
}
