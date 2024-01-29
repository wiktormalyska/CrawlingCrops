package org.lostarmy.map;

import org.lostarmy.map.CellTypes.CaveFloor;
import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.CellTypes.Grass;
import org.lostarmy.map.CellTypes.Marker;
import org.lostarmy.utils.ConsoleColors;

import java.util.Random;

public interface MapHandlerUtils {
    default void printMap(Cell[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.print(ConsoleColors.RESET);
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j].getDisplay());
            }
            System.out.println();
        }
    }
    default void saveCopy(Cell[][] map, Cell[][] mapCopy) {
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, mapCopy[0].length);
        }
    }
    default void drawCircle(int centerX, int centerY, int radius, Cell cellType, Cell[][] map) {
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x * x + y * y <= radius * radius) {
                    setCellIfWithinBounds(centerX + x, centerY + y, cellType, map);
                }
            }
        }
    }

   private void setCellIfWithinBounds(int x, int y, Cell cellType, Cell[][] map) {
        if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
            map[x][y] = cellType;
        }
    }

    default Cell findFurtherstFreeCell(int mapSizeX, int mapSizeY, Cell[][] map) {
        Random random = new Random();
        int corner = random.nextInt(4); // Generate a random number between 0 and 3

        int x, y;
        y = switch (corner) {
            case 0 -> {
                x = 0;
                yield 0; // Top-left corner
            }
            case 1 -> {
                x = mapSizeX - 1;
                yield 0; // Top-right corner
            }
            case 2 -> {
                x = 0;
                yield mapSizeY - 1; // Bottom-left corner
            }
            default -> {
                x = mapSizeX - 1;
                yield mapSizeY - 1; // Bottom-right corner
            }
        };

        Cell furtherst = map[x][y];
        double distance = 0;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof CaveFloor || map[i][j] instanceof Grass) {
                    double newDistance = calculateDistance(x, y, i, j);
                    if (newDistance >= distance) {
                        distance = newDistance;
                        furtherst = map[i][j];
                    }
                }
            }
        }
        return furtherst;
    }

    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    default Cell findClosestFreeCell(Cell[][] map) {
        int mapSizeX = map.length;
        int mapSizeY = map[0].length;
        int x = mapSizeX / 2;
        int y = mapSizeY / 2;
        Cell closest = map[x][y];
        double distance = mapSizeX;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof CaveFloor || map[i][j] instanceof Grass) {
                    double newDistance = calculateDistance(x, y, i, j);
                    if (newDistance <= distance) {
                        distance = newDistance;
                        closest = map[i][j];
                    }
                }
            }
        }
        return closest;
    }

    default Cell findCenter(Cell [][] map) {
        int mapSizeX = map.length;
        int mapSizeY = map[0].length;
        int x = mapSizeX / 2;
        int y = mapSizeY / 2;
        Cell center = map[x][y];
        double distance = mapSizeX;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof Marker marker) {
                    if (marker.color.equals(ConsoleColors.GREEN)) {
                        double newDistance = calculateDistance(x, y, i, j);
                        if (newDistance <= distance) {
                            distance = newDistance;
                            center = map[i][j];
                        }
                    }
                }
            }
        }


        return center;
    }
    default Cell findClosest(int x1, int y1, Cell[][] map) {
        int mapSizeX = map.length;
        int mapSizeY = map[0].length;
        Cell closest = map[x1][y1];
        double distance = 9999;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof Marker marker) {
                    if (marker.color.equals(ConsoleColors.GREEN)) {
                        double newDistance = calculateDistance(x1, y1, i, j);
                        if (newDistance <= distance) {
                            distance = newDistance;
                            closest = map[i][j];
                        }
                    }
                }
            }
        }
        return closest;
    }

}
