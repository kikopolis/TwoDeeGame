package com.kikopolis.tile;

import com.google.inject.Inject;

import java.awt.image.BufferedImage;

public class Tile {
    private final String name;
    private final BufferedImage image;
    private final boolean solid;
    
    @Inject
    public Tile() {
        name = null;
        image = null;
        solid = false;
    }
    
    public Tile(final String name, final BufferedImage image, final boolean solid) {
        this.name = name;
        this.image = image;
        this.solid = solid;
    }
    
    public String getName() {
        return name;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public boolean isSolid() {
        return solid;
    }
    
    @Override
    public String toString() {
        return "Tile{" +
               "name='" + name + '\'' +
               ", image=" + image +
               ", solid=" + solid +
               '}';
    }
}
