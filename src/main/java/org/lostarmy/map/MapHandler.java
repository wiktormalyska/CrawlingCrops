package org.lostarmy.map;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.map.CellTypes.*;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.util.List;
import java.util.Random;

public class MapHandler implements MapHandlerUtils {
    private final Cell[][] map;
    private final Cell[][] mapCopy;
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
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(mapCopy[i], 0, map[i], 0, map[0].length);
        }
        if (nextLevel) {
            switch (level) {
                case 1 -> generateForestMap();
                case 2, 3, 4, 5 -> generateCaveMap();
            }
            nextLevel = false;
        }
    }

    public void generateCaveMap() {
        generateBorder();
        generateTrapdor();

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                map[i][j] = new Wall(i, j);
            }
        }
        //printMap();

        for (int i = 2; i < map.length - 2; i++) {
            for (int j = 2; j < map[0].length - 2; j++) {
                if (Math.random() < 0.01 && (i % 3 == 0 || j % 3 == 0)) {
                    map[i][j] = new Marker(i, j, ConsoleColors.GREEN);
                } else {
                    map[i][j] = new Wall(i, j);
                }
            }
        }
        //printMap();

        Cell center = findCenter(map);
        map[center.getX()][center.getY()] = new Marker(center.getX(), center.getY(), ConsoleColors.RED);
        while (areFreePoints()) {
            Cell closest = findClosest(center.getX(), center.getY(), map);
            drawLine(center.getX(), center.getY(), closest.getX(), closest.getY());
            center = closest;
            //printMap();
        }

        generateBorder();
        generateTrapdor();
        saveCopy(map, mapCopy);
    }



    private void generateTrapdor() {
        Cell furthest = findFurtherstFreeCell(mapSizeX, mapSizeY, map);
        map[furthest.getX()][furthest.getY()] = new TrapDor(furthest.getX(), furthest.getY());
    }

    public void generateForestMap() {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                map[i][j] = new Grass(i, j);
            }
        }

        for (int i = 2; i < map.length - 2; i++) {
            for (int j = 2; j < map[0].length - 2; j++) {
                if (Math.random() < 0.07) {
                    if (!(map[i - 1][j] instanceof Tree || map[i + 1][j] instanceof Tree || map[i][j - 1] instanceof Tree || map[i][j + 1] instanceof Tree || map[i - 1][j - 1] instanceof Tree || map[i + 1][j + 1] instanceof Tree || map[i - 1][j + 1] instanceof Tree || map[i + 1][j - 1] instanceof Tree)) {
                        map[i][j] = new Tree(i, j);
                    }
                }
            }
        }

        generateStoneBorder();

        int radius = 6;
        drawCircle(0, 0, radius, new Wall(0, 0), map);
        drawCircle(0, map[0].length - 1, radius, new Wall(0, map[0].length - 1), map);
        drawCircle(map.length - 1, 0, radius, new Wall(map.length - 1, 0), map);
        drawCircle(map.length - 1, map[map.length - 1].length - 1, radius, new Wall(map.length - 1, map[map.length - 1].length - 1), map);
        generateBorder();
        generateTrapdor();
        saveCopy(map, mapCopy);
    }

    private void generateStoneBorder() {
        Random random = new Random();
        int numberOfWaves = random.nextInt(5) + 1;

        for (int wave = 0; wave < numberOfWaves; wave++) {
            int amplitude = random.nextInt(5) + 2;
            double frequency = random.nextDouble() * 0.2 + 0.05;

            for (int i = 0; i < map.length; i++) {
                int height = (int) (amplitude * Math.sin(frequency * i));
                //Generate Left
                for (int j = 0; j < height; j++) {
                    map[j][i] = new Wall(j, i);
                }
                //Generate Right
                for (int j = map.length - 1; j > map.length - 1 - height; j--) {
                    map[j][i] = new Wall(j, i);
                }
            }

            for (int i = 0; i < map[0].length; i++) {
                int height = (int) (amplitude * Math.sin(frequency * i));
                //Generate Top
                for (int j = 0; j < height; j++) {
                    map[i][j] = new Wall(i, j);
                }
                //Generate Bottom
                for (int j = map[0].length - 1; j > map[0].length - 1 - height; j--) {
                    map[i][j] = new Wall(i, j);
                }
            }
        }
    }


    private boolean areFreePoints() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] instanceof Marker marker) {
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
            map[x1 + 1][y1] = new CaveFloor(x1 + 1, y1);
            map[x1 - 1][y1] = new CaveFloor(x1 - 1, y1);
            map[x1][y1 + 1] = new CaveFloor(x1, y1 + 1);
            map[x1][y1 - 1] = new CaveFloor(x1, y1 - 1);
            map[x1 + 1][y1 + 1] = new CaveFloor(x1 + 1, y1 + 1);
            map[x1 + 1][y1 - 1] = new CaveFloor(x1 + 1, y1 - 1);
            map[x1 - 1][y1 + 1] = new CaveFloor(x1 - 1, y1 + 1);
            map[x1 - 1][y1 - 1] = new CaveFloor(x1 - 1, y1 - 1);

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






    public int getNumberOfFreeSpaces() {
        int result = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < mapCopy[0].length; j++) {
                if (map[i][j] instanceof CaveFloor || map[i][j] instanceof Grass) {
                    result++;
                }
            }
        }
        return result;
    }




    public void generateLayingItems() {
        for (LayingItem item : HandlersManager.layingItemHandler.itemsOnGround) {
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
        renderLayingItems();
    }


    private void renderEntities(EntityHandler entityHandler) {
        List<Enemy> entities = entityHandler.getEnemies();
        for (Enemy entity : entities) {
            map[entity.getX()][entity.getY()] = entity;
        }
        map[entityHandler.getPlayer().getX()][entityHandler.getPlayer().getY()] = entityHandler.getPlayer();
    }

    private void renderLayingItems() {
        for (LayingItem item : HandlersManager.layingItemHandler.itemsOnGround) {
            map[item.getX()][item.getY()] = item;
        }
    }
}
