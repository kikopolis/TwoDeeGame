package com.kikopolis.object;

import com.google.inject.Inject;
import com.kikopolis.sprite.Sprite;

public class OBJ_Key extends AbstractObject {
    @Inject
    public OBJ_Key(final int worldX, final int worldY, final Sprite sprite) {
        this.name = "Key";
        this.collision = true;
        this.worldX = worldX;
        this.worldY = worldY;
        this.sprite = sprite;
    }
}
