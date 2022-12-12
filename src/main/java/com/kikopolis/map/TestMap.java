package com.kikopolis.map;

public class TestMap extends AbstractMap {
    public TestMap() {
        super(50, 50, 16, 12, "test", "Test Map", getTestMap());
    }
    
    private static String[][] getTestMap() {
        var mapData = new String[16][12];
        mapData[0] = new String[]{"tile_earth", "tile_earth", "tile_earth", "tile_water01", "tile_water01", "tile_water01", "tile_water01", "tile_water01", "tile_water01", "tile_water01", "tile_water01", "tile_water01"};
        mapData[1] = new String[]{"tile_grass00", "tile_grass00", "tile_grass00", "tile_hut", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_hut", "tile_grass00", "tile_grass00"};
        mapData[2] = new String[]{"tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00"};
        mapData[3] = new String[]{"tile_grass00", "tile_grass00", "tile_hut", "tile_grass00", "tile_hut", "tile_grass00", "tile_wall", "tile_wall", "tile_wall", "tile_wall", "tile_wall", "tile_wall"};
        mapData[4] = new String[]{"tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_hut", "tile_grass00", "tile_floor01", "tile_floor01", "tile_floor01", "tile_floor01", "tile_floor01", "tile_floor01"};
        mapData[5] = new String[]{"tile_grass00", "tile_hut", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_wall", "tile_wall", "tile_wall", "tile_wall", "tile_wall", "tile_wall"};
        mapData[6] = new String[]{"tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00", "tile_grass00"};
        mapData[7] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[8] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[9] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[10] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[11] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[12] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[13] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[14] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        mapData[15] = new String[]{"tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_tree", "tile_grass00", "tile_grass00"};
        return mapData;
    }
}
