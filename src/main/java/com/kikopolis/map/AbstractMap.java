package com.kikopolis.map;

public abstract class AbstractMap {
    protected final int startX;
    protected final int startY;
    protected final int rows;
    protected final int columns;
    protected final String name;
    protected final String displayName;
    protected final String[][] tileData;
    
    protected AbstractMap(
            final int startX,
            final int startY,
            final int rows,
            final int columns,
            final String name,
            final String displayName,
            final String[][] tileData
                      ) {
        this.startX = startX;
        this.startY = startY;
        this.rows = rows;
        this.columns = columns;
        this.name = name;
        this.displayName = displayName;
        this.tileData = tileData;
    }
    
    public int getStartX() {
        return startX;
    }
    
    public int getStartY() {
        return startY;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String[][] getTileData() {
        return tileData;
    }
    
    public String getTileName (final int row, final int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return null;
        }
        return tileData[row][column];
    }
}
