package org.lostarmy.map;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.map.CellTypes.*;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.util.List;

public class MapHandler {
    private final Cell[][] map;
    private Cell[][] mapCopy;
    public int level = 1;
    public boolean nextLevel = false;

    public Cell[][] getMap() {
        return map;
    }

    public static int mapSizeX, mapSizeY;

    public MapHandler(int sizeX, int sizeY) {
        mapSizeX = sizeX;
        mapSizeY = sizeY;
        map = new Cell[sizeX + 2][sizeY + 2];
        mapCopy = new Cell[sizeX + 2][sizeY + 2];
    }

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public void generateMap() {
        //Fill Grass
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = mapCopy[i][j];
            }
        }
        if (nextLevel) {
            switch (level) {
                case 1 -> {
                    generateForestMap();
                }
                case 2 -> {
                    generateCaveMap();
                }
            }
            nextLevel = false;
        }
        //Fill Laying Items
            generateLayingItems();
    }

    public void generateCaveMap() {
        //fill with wall
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                map[i][j] = new Wall(i, j);
            }
        }
        //generate random points
        for (int i = 2; i < map.length - 2; i++) {
            for (int j = 2; j < map[0].length - 2; j++) {
                if (Math.random() < 0.01 && (i % 3 == 0 || j % 3 == 0)) {
                    map[i][j] = new Marker(i, j, ConsoleColors.GREEN);
                } else {
                    map[i][j] = new Wall(i, j);
                }
            }
        }
        //get closest to center
        Cell center = findCenter();
        map[center.getX()][center.getY()] = new Marker(center.getX(), center.getY(), ConsoleColors.RED);
        while (areFreePoints()) {
            //draw line from center to closest
            Cell closest = findClosest(center.getX(), center.getY());
            drawLine(center.getX(), center.getY(), closest.getX(), closest.getY());
            center = closest;
        }

       generateBorder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }
    }
    public void generateForestMap() {
    // Fill the entire map with Grass cells
    for (int i = 1; i < map.length - 1; i++) {
        for (int j = 1; j < map[0].length - 1; j++) {
            map[i][j] = new Grass(i, j);
        }
    }

    // Generate random points and replace them with Tree cells
    for (int i = 2; i < map.length - 2; i++) {
        for (int j = 2; j < map[0].length - 2; j++) {
            if (Math.random() < 0.1) { // 10% chance to become a tree
                map[i][j] = new Tree(i, j);
            }
        }
    }

    // Fill the border of the map with Wall cells
    generateBorder();

    // Copy the generated map to mapCopy
    for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < mapCopy[0].length; j++) {
            mapCopy[i][j] = map[i][j];
        }
    }
}

    private boolean areFreePoints() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] instanceof Marker) {
                    Marker marker = (Marker) map[i][j];
                    if (marker.color.equals(ConsoleColors.GREEN))
                        return true;
                }
            }
        }
        return false;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;
        int e2;

        while (true) {
            // Draw line at position (x1, y1)
            map[x1][y1] = new CaveFloor(x1, y1);
            map[x1 + 1][y1] = new CaveFloor(x1+1, y1);
            map[x1 - 1][y1] = new CaveFloor(x1-1, y1);
            map[x1][y1 + 1] = new CaveFloor(x1, y1+1);
            map[x1][y1 - 1] = new CaveFloor(x1, y1-1);
            map[x1 + 1][y1+1] = new CaveFloor(x1+1, y1+1);
            map[x1 + 1][y1-1] = new CaveFloor(x1+1, y1-1);
            map[x1 -1][y1+1] = new CaveFloor(x1-1, y1+1);
            map[x1 -1][y1-1] = new CaveFloor(x1 -1, y1-1);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }

    private void showCenter() {
        map[mapSizeX / 2][mapSizeY / 2] = new Marker(mapSizeX / 2, mapSizeY / 2, ConsoleColors.PURPLE);
    }
    public Cell findClosestToCenter(){
        return findClosest(mapSizeX / 2, mapSizeY / 2);
    }

    private Cell findCenter() {
        int x = mapSizeX / 2;
        int y = mapSizeY / 2;
        Cell center = map[x][y];
        double distance = mapSizeX;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof Marker) {
                    Marker marker = (Marker) map[i][j];
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

    private Cell findClosest(int x1, int y1) {
        Cell closest = map[x1][y1];
        double distance = 9999;
        for (int i = 0; i < mapSizeX; i++) {
            for (int j = 0; j < mapSizeY; j++) {
                if (map[i][j] instanceof Marker) {
                    Marker marker = (Marker) map[i][j];
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

    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private void generateLayingItems() {
        for (LayingItem item : LayingItemHandler.itemsOnGround) {
            map[item.getX()][item.getY()] = item;
        }
    }

    private void generateBorder() {
        for (int i = 1; i < map.length - 1; i++) {
            //Generate Left
            map[i][0] = new Border(i, 0, BorderType.LEFT);
        }
        for (int i = 1; i < map.length - 1; i++) {
            //Generate Right
            map[i][map[i].length - 1] = new Border(i, map[i].length - 1, BorderType.RIGHT);
        }
        for (int i = 1; i < map[0].length - 1; i++) {
            //Generate Top
            map[0][i] = new Border(0, i, BorderType.TOP);
        }
        for (int i = 1; i < map.length - 1; i++) {
            //Generate Bottom
            map[map.length - 1][i] = new Border(map.length - 1, i, BorderType.BOTTOM);
        }
        map[0][0] = new Border(0, 0, BorderType.TOP_LEFT);
        map[0][map[0].length - 1] = new Border(0, map[0].length - 1, BorderType.TOP_RIGHT);
        map[map.length - 1][0] = new Border(map.length - 1, 0, BorderType.BOTTOM_LEFT);
        map[map.length - 1][map[map.length - 1].length - 1] = new Border(map.length - 1, map[map.length - 1].length - 1, BorderType.BOTTOM_RIGHT);
    }

    public void update() {
        renderEntities(HandlersManager.entityHandler);
    }


    private void renderEntities(EntityHandler entityHandler) {
        List<Entity> entities = entityHandler.getEntities();
        for (Entity entity : entities) {
            map[entity.getX()][entity.getY()] = entity;
        }
    }
}
