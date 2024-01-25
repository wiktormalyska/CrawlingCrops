package org.lostarmy.map;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.map.CellTypes.*;
import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHandler {
    private final Cell[][] map;

    public Cell[][] getMap() {
        return map;
    }

    public static int mapSizeX, mapSizeY;
    private final ScreenHandler screenHandler;
    public MapHandler(int sizeX, int sizeY, ScreenHandler screenHandler){
        this.screenHandler = screenHandler;
        mapSizeX=sizeX;
        mapSizeY=sizeY;
        map = new Cell[sizeX+2][sizeY+2];
    }
    public Cell getCell(int x, int y){
        return map[x][y];
    }

    public void generateEmptyMap(){
        //Fill Grass
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                map[i][j]=new Grass(i, j);
            }
        }
        //Fill Border
        generateBorder();
        //Fill Laying Items
        if(HandlersManager.layingItemHandler != null) {
            generateLayingItems();
        }
    }
    public void generateCaveMap(){
        for (int i=1;i<map.length-1;i++){
            for (int j=1;j<map[0].length-1;j++){
                map[i][j]=new Wall(i, j);
            }
        }
        for (int i=2;i<map.length-2;i++){
            for (int j=2;j<map[0].length-2;j++){
                if (Math.random()<0.01 && (i%4==0 || j%4==0)){
                    map[i][j]=new Marker(i, j, ConsoleColors.GREEN);
                } else {
                    map[i][j]=new Wall(i, j);
                }
            }
        }

        Cell center = findCenter();
        map[center.getX()][center.getY()]=new Marker(center.getX(), center.getY(), ConsoleColors.RED);
        Cell center2 = findCenter();
        map[center2.getX()][center2.getY()]=new Marker(center2.getX(), center2.getY(), ConsoleColors.RED);
        //showCenter();

        drawLine(center.getX(), center.getY(), center2.getX(), center2.getY());
        generateBorder();
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
    private void showCenter(){

        map[mapSizeX/2][mapSizeY/2]=new Marker(mapSizeX/2, mapSizeY/2, ConsoleColors.PURPLE);
    }
    private Cell findCenter(){
        int x = mapSizeX/2;
        int y = mapSizeY/2;
        Cell center = map[x][y];
        double distance = mapSizeX;
        for (int i=0;i<mapSizeX;i++){
            for (int j=0;j<mapSizeY;j++){
                if (map[i][j] instanceof Marker){
                    Marker marker = (Marker) map[i][j];
                    if (marker.color.equals(ConsoleColors.GREEN)){
                        double newDistance = calculateDistance(x, y, i, j);
                        if (newDistance<=distance){
                            distance=newDistance;
                            center=map[i][j];
                        }
                    }
                }
            }
        }


        return center;
    }

    private Cell findClosest(int x1, int y1, int x2, int y2){
        Cell closest = map[x1][y1];
        double distance = calculateDistance(x1, y1, x2, y2);
        for (int i=0;i<mapSizeX;i++){
            for (int j=0;j<mapSizeY;j++){
                if (map[i][j] instanceof CaveFloor){
                    double newDistance = calculateDistance(x1, y1, i, j);
                    if (newDistance<=distance){
                        distance=newDistance;
                        closest=map[i][j];
                    }
                }

            }
        }
        return closest;
    }
    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private void generateLayingItems(){
        for (LayingItem item : LayingItemHandler.itemsOnGround){
            map[item.getX()][item.getY()]=item;
        }
    }

    private void generateBorder(){
    for (int i=1;i<map.length-1;i++) {
        //Generate Left
        map[i][0] = new Border(i, 0, BorderType.LEFT);
    }
    for (int i=1;i<map.length-1;i++) {
        //Generate Right
        map[i][map[i].length - 1] = new Border(i, map[i].length - 1, BorderType.RIGHT);
    }
    for (int i=1;i<map[0].length-1;i++) {
        //Generate Top
        map[0][i] = new Border(0, i, BorderType.TOP);
    }
    for (int i=1;i<map.length-1;i++){
        //Generate Bottom
        map[map.length-1][i]=new Border(map.length-1, i, BorderType.BOTTOM);
    }
    map[0][0]=new Border(0,0,BorderType.TOP_LEFT);
    map[0][map[0].length-1]=new Border(0,map[0].length-1,BorderType.TOP_RIGHT);
    map[map.length-1][0]=new Border(map.length-1,0,BorderType.BOTTOM_LEFT);
    map[map.length-1][map[map.length-1].length-1]=new Border(map.length-1,map[map.length-1].length-1,BorderType.BOTTOM_RIGHT);
}
    public void update(EntityHandler entityHandler){
        renderEntities(entityHandler);
    }




    private void renderEntities(EntityHandler entityHandler){
        List<Entity> entities = entityHandler.getEntities();
        for (Entity entity:entities){
            map[entity.getX()][entity.getY()]=entity;
        }
    }
}
