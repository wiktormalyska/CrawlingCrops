package org.lostarmy.map;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.map.CellTypes.Border;
import org.lostarmy.map.CellTypes.BorderType;
import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.CellTypes.Grass;
import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

import java.util.List;

public class MapHandler {
    private Cell[][] map;
    public static int mapSizeX, mapSizeY;
    private ScreenHandler screenHandler;
    public MapHandler(int sizeX, int sizeY, ScreenHandler screenHandler){
        this.screenHandler = screenHandler;
        mapSizeX=sizeX;
        mapSizeY=sizeY;
        map = new Cell[sizeX+2][sizeY+2];
    }
    public Cell getCell(int x, int y){
        return map[x][y];
    }

    public void generateMap(){
        //Fill Grass
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                map[i][j]=new Grass(i, j);
            }
        }
        //Fill Border
        generateBorder();
        //Fill Laying Items
        generateLayingItems();
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
            map[i][map[0].length - 1] = new Border(i, map[0].length - 1, BorderType.RIGHT);
        }
        for (int i=1;i<map.length-1;i++) {
            //Generate Top
            map[0][i] = new Border(0, i, BorderType.TOP);
        }
        for (int i=1;i<map.length-1;i++){
            //Generate Bottom
            map[map[0].length-1][i]=new Border(map[0].length-1, i, BorderType.BOTTOM);
        }
        map[0][0]=new Border(0,0,BorderType.TOP_LEFT);
        map[0][map.length-1]=new Border(0,0,BorderType.TOP_RIGHT);
        map[map.length-1][0]=new Border(0,0,BorderType.BOTTOM_LEFT);
        map[map.length-1][map.length-1]=new Border(0,0,BorderType.BOTTOM_RIGHT);
    }
    public void update(EntityHandler entityHandler){
        renderEntities(entityHandler);
    }


    public void render(){
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[0].length;j++){
                screenHandler.setCell(map[i][j], i, j);
            }
        }
    }

    private void renderEntities(EntityHandler entityHandler){
        List<Entity> entities = entityHandler.getEntities();
        for (Entity entity:entities){
            map[entity.getX()][entity.getY()]=entity;
        }
    }
}
