package com.kikopolis.sprite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kikopolis.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Sprite {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("resourcePath")
    private String resourcePath;
    @JsonIgnore
    private BufferedImage image;
    
    public Sprite() {
        this(null, null, null);
    }
    
    public Sprite(final String id, final String name, final String resourcePath) {
        this.id = id;
        this.name = name;
        this.resourcePath = resourcePath;
        if (resourcePath != null) {
            image = ImageLoader.loadImageResource(resourcePath);
        }
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getResourcePath() {
        return resourcePath;
    }
    
    public void setResourcePath(final String resourcePath) {
        this.resourcePath = resourcePath;
    }
    
    public void loadImage() {
        image = ImageLoader.loadImageResource(resourcePath);
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(final BufferedImage image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Sprite{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", resourcePath='" + resourcePath + '\'' +
               ", image=" + image +
               '}';
    }
}
