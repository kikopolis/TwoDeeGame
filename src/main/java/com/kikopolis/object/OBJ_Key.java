package com.kikopolis.object;

import com.google.inject.Inject;
import com.kikopolis.sprite.Sprite;

public class OBJ_Key extends AbstractObject {
    public static final String NAME_ID = "object_key";
    
    @Inject
    public OBJ_Key() {
        this(0, 0, null);
    }
    
    public OBJ_Key(final int worldX, final int worldY, final Sprite sprite) {
        super(NAME_ID, sprite, false, worldX, worldY);
    }
}
