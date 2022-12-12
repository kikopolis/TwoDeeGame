package com.kikopolis.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.kikopolis.config.SpriteConfig;
import com.kikopolis.entity.AbstractEntity;
import com.kikopolis.entity.Player;
import com.kikopolis.object.AbstractObject;
import com.kikopolis.object.OBJ_Key;
import com.kikopolis.sprite.Sprite;
import com.kikopolis.tile.Tile;
import com.kikopolis.tile.TileSpriteList;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Bertram, from English meaning "Bright. Industrious", is the asset manager for the game.
 */
public class Bertram implements AssetManager {
    // todo this is the asset manager
    // todo this needs to manage entities, objects, and tiles
    // todo this needs to cache assets
    // todo this needs to load assets from disk
    // todo this needs to load assets
    // todo this needs to unload assets
    private static final Logger LOGGER = getLogger(Bertram.class);
    private final String spriteMapResourcePath;
    private final ObjectMapper objectMapper;
    private final Map<String, Sprite> spriteMap;
    private final TileSpriteList tileSpriteList;
    private List<AbstractEntity> entities;
    private List<AbstractObject> objects;
    private List<Tile> tiles;
    
    @Inject
    public Bertram(final ObjectMapper objectMapper, final TileSpriteList tileSpriteList) {
        spriteMapResourcePath = SpriteConfig.SPRITE_DIRECTORY + SpriteConfig.SPRITE_MAP_FILE;
        this.objectMapper = objectMapper;
        this.tileSpriteList = tileSpriteList;
        spriteMap = new HashMap<>();
        entities = new ArrayList<>();
        objects = new ArrayList<>();
        tiles = new ArrayList<>();
    }
    
    @Override
    public Sprite getSpriteByName(final String name) {
        for (Sprite sprite : spriteMap.values()) {
            if (sprite.getName().equals(name)) {
                return sprite;
            }
        }
        return null;
    }
    
    public void loadSprites() {
        try {
            var spriteArray = objectMapper.readValue(getClass().getResource(spriteMapResourcePath), Sprite[].class);
            for (var sprite : spriteArray) {
                sprite.loadImage();
                spriteMap.put(sprite.getId(), sprite);
            }
            prettyPrintSpriteMap(spriteMap);
        } catch (IOException e) {
            LOGGER.error("Error loading sprite map", e);
        }
    }
    
    public void loadEntities() {
        entities.add(loadPlayer());
    }
    
    public AbstractEntity getEntityByName(final String name) {
        return entities.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
    
    public void loadObjects() {
        var key = new OBJ_Key();
        key.setSprite(getSpriteByName(OBJ_Key.NAME_ID));
        objects.add(key);
    }
    
    public AbstractObject getObjectByName(final String name) {
        return objects.stream().filter(o -> o.getName().equals(name)).findFirst().orElse(null);
    }
    
    public Tile getTile(final String name) {
        var tile = tiles.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
        if (tile == null) {
            tile = new Tile(name, null, true);
        }
        return tile;
    }
    
    public void loadTiles() {
        final var collisionTileNames = tileSpriteList.getCollisionTileNames();
        final var walkableTileNames = tileSpriteList.getWalkableTileNames();
        for (final var collisionTileName : collisionTileNames) {
            tiles.add(new Tile(
                    collisionTileName,
                    getSpriteByName(collisionTileName),
                    true
            ));
        }
        for (final var walkableTileName : walkableTileNames) {
            tiles.add(new Tile(
                    walkableTileName,
                    getSpriteByName(walkableTileName),
                    false
            ));
        }
        prettyPrintTileList(tiles);
    }
    
    private AbstractEntity loadPlayer() {
        var player = new Player();
        player.setUp1(getSpriteByName("player_up_1"));
        player.setUp2(getSpriteByName("player_up_2"));
        player.setDown1(getSpriteByName("player_down_1"));
        player.setDown2(getSpriteByName("player_down_2"));
        player.setLeft1(getSpriteByName("player_left_1"));
        player.setLeft2(getSpriteByName("player_left_2"));
        player.setRight1(getSpriteByName("player_right_1"));
        player.setRight2(getSpriteByName("player_right_2"));
        return player;
    }
    
    private void prettyPrintSpriteMap(final Map<String, Sprite> spriteMap) {
        LOGGER.debug("Sprite Map:");
        for (final var sprite : spriteMap.values()) {
            LOGGER.debug("{}", sprite.toString());
        }
    }
    
    private void prettyPrintTileList(final List<Tile> filteredTileList) {
        LOGGER.debug("Sprite Map:");
        for (final var tile : filteredTileList) {
            LOGGER.debug("{}", tile.toString());
        }
    }
}
