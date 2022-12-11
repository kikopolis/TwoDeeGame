package com.kikopolis.object;

import com.kikopolis.sprite.Sprite;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class AbstractObject {
    protected static final Logger LOGGER = getLogger(AbstractObject.class);
    protected Sprite sprite;
    protected String name;
    protected boolean collision;
    protected int worldX;
    protected int worldY;
}
