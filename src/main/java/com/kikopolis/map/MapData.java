package com.kikopolis.map;

import java.util.Arrays;

public record MapData(int startX, int startY, int endX, int endY, int rows, int columns, String[][] tiles) {
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final var other = (MapData) obj;
        return startX == other.startX
               && startY == other.startY
               && endX == other.endX
               && endY == other.endY
               && rows == other.rows
               && columns == other.columns
               && Arrays.deepEquals(tiles, other.tiles);
    }
    
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + startX;
        result = 31 * result + startY;
        result = 31 * result + endX;
        result = 31 * result + endY;
        result = 31 * result + rows;
        result = 31 * result + columns;
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }
    
    @Override
    public String toString() {
        return "MapData(startX="
               + startX
               + ", startY="
               + startY
               + ", endX="
               + endX
               + ", endY="
               + endY
               + ", rows="
               + rows
               + ", columns="
               + columns
               + ", tiles="
               + Arrays.deepToString(tiles)
               + ")";
    }
}

