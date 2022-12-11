package com.kikopolis.tile;

import com.google.inject.Inject;
import org.apache.commons.lang3.ArrayUtils;

public class TileSpriteList {
    private final String[] walkableTileNames;
    private final String[] collisionTileNames;
    private final String[] fullTileNames;
    
    @Inject
    public TileSpriteList() {
        walkableTileNames = walkableTileList();
        collisionTileNames = collisionTileList();
        fullTileNames = fullTileList();
    }
    
    public String[] getWalkableTileNames() {
        return walkableTileNames;
    }
    
    public String[] getCollisionTileNames() {
        return collisionTileNames;
    }
    
    public String[] getFullTileNames() {
        return fullTileNames;
    }
    
    private String[] walkableTileList() {
        var list = new String[18];
        list[0] = "tile_earth";
        list[1] = "tile_floor01";
        list[2] = "tile_grass00";
        list[3] = "tile_grass01";
        list[4] = "tile_road00";
        list[5] = "tile_road01";
        list[6] = "tile_road02";
        list[7] = "tile_road03";
        list[8] = "tile_road04";
        list[9] = "tile_road05";
        list[10] = "tile_road06";
        list[11] = "tile_road07";
        list[12] = "tile_road08";
        list[13] = "tile_road09";
        list[14] = "tile_road10";
        list[15] = "tile_road11";
        list[16] = "tile_road12";
        list[17] = "tile_sand";
        return list;
    }
    
    private String[] collisionTileList() {
        var list = new String[17];
        list[0] = "tile_hut";
        list[1] = "tile_table01";
        list[2] = "tile_tree";
        list[3] = "tile_wall";
        list[4] = "tile_water00";
        list[5] = "tile_water01";
        list[6] = "tile_water02";
        list[7] = "tile_water03";
        list[8] = "tile_water04";
        list[9] = "tile_water05";
        list[10] = "tile_water06";
        list[11] = "tile_water07";
        list[12] = "tile_water08";
        list[13] = "tile_water10";
        list[14] = "tile_water11";
        list[15] = "tile_water12";
        list[16] = "tile_water13";
        return list;
    }
    
    private String[] fullTileList() {
        return ArrayUtils.addAll(walkableTileNames, collisionTileNames);
    }
}
