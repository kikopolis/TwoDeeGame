package com.kikopolis.map;

import java.util.Random;

public abstract class WorldMap {
    private static final String[]
            floorTiles =
            {
                    "earth",
                    "grass00",
                    "grass01",
                    "sand",
                    "road00",
                    "road01",
                    "road02",
                    "water00",
                    "wall",
                    "tree",
                    "hut",
                    "water01"
            };
    private static final Random random = new Random();
    
    public static String[][] getMapData() {
        var mapData = new String[100][100];
        for (var row = 0; row < mapData.length; row++) {
            for (var column = 0; column < mapData[row].length; column++) {
                String tile = floorTiles[random.nextInt(floorTiles.length)];
                mapData[row][column] = tile;
            }
        }
        return ensurePlayerCanMove(mapData);
    }
    
    public static String[][] ensurePlayerCanMove(String[][] mapData) {
        // if too many tiles are impassable, then the player will be stuck
        // impassable tiles for testing are wall, tree, hut, water00, water01
        for (var row = 0; row < mapData.length; row++) {
            for (var column = 0; column < mapData[row].length; column++) {
                if (row > 1 && row < mapData.length - 2 && column > 1 && column < mapData[row].length - 2) {
                    if (mapData[row + 1][column].equals("wall")
                        || mapData[row + 1][column].equals("tree")
                        || mapData[row + 1][column].equals("hut")
                        || mapData[row + 1][column].equals("water00")
                        || mapData[row + 1][column].equals("water01")
                        || mapData[row - 1][column].equals("wall")
                        || mapData[row - 1][column].equals("tree")
                        || mapData[row - 1][column].equals("hut")
                        || mapData[row - 1][column].equals("water00")
                        || mapData[row - 1][column].equals("water01")
                        || mapData[row][column + 1].equals("wall")
                        || mapData[row][column + 1].equals("tree")
                        || mapData[row][column + 1].equals("hut")
                        || mapData[row][column + 1].equals("water00")
                        || mapData[row][column + 1].equals("water01")
                        || mapData[row][column - 1].equals("wall")
                        || mapData[row][column - 1].equals("tree")
                        || mapData[row][column - 1].equals("hut")
                        || mapData[row][column - 1].equals("water00")
                        || mapData[row][column - 1].equals("water01")) {
                        mapData[row][column] = "grass00";
                    }
                }
            }
        }
        return mapData;
    }
}
