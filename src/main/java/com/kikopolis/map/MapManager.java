package com.kikopolis.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MapManager {
    private static final Logger LOGGER = getLogger(MapManager.class);
    private static final String MAP_PATH = "/maps/";
    private static final String MAP_EXTENSION = ".map";
    private final ObjectMapper objectMapper;
    private final String[] mapProgression;
    private AbstractMap[] maps;
    
    @Inject
    public MapManager(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        mapProgression = mapProgression();
        maps = new AbstractMap[0];
        
        // TODO: this is for testing only
//        var tiles = List.of("tile_earth", "tile_grass00", "tile_grass01", "tile_sand");
//        var forest01data = new String[100][100];
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < 100; j++) {
//                forest01data[i][j] = tiles.get((int) (Math.random() * tiles.size()));
//            }
//        }
//        saveTileData("Forest01", forest01data);
//        var forest02data = new String[100][100];
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < 100; j++) {
//                forest02data[i][j] = tiles.get((int) (Math.random() * tiles.size()));
//            }
//        }
//        saveTileData("Forest02", forest02data);
    }
    
    public AbstractMap getMap(final String name) {
        for (AbstractMap map : maps) {
            if (map.getName().equals(name)) {
                return map;
            }
        }
        return null;
    }
    
    public void loadMaps() {
        var list = new AbstractMap[2];
        var forest01 = new Forest01(loadTileData("Forest01"));
        list[0] = forest01;
        var forest02 = new Forest01(loadTileData("Forest02"));
        list[1] = forest02;
        maps = list;
    }
    
    public String[] getMapProgression() {
        return mapProgression;
    }
    
    private String[] mapProgression() {
        return new String[]{
                "Forest01",
                "Forest02"
        };
    }
//    private void saveTileData(final String name, final String[][] tileData) {
//        try {
//            var path = MAP_PATH + name + MAP_EXTENSION;
//            var resource = getClass().getResource(path);
//            var file = new File(resource.getFile());
//            objectMapper.writeValue(file, tileData);
//        } catch (IOException e) {
//            LOGGER.error("Error saving map: {}", e.getMessage());
//        }

//    }
    
    private String[][] loadTileData(final String name) {
        try {
            var path = MAP_PATH + name + MAP_EXTENSION;
            var resource = getClass().getResource(path);
            return objectMapper.readValue(resource, String[][].class);
        } catch (Exception e) {
            LOGGER.error("Error loading map: {}", e.getMessage());
        }
        return new String[0][0];
    }
}
