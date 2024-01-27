package org.lostarmy.utils;

import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.map.MapHandler;
import org.lostarmy.screen.ScreenHandler;

public class HandlersManager {
    public static void init(ScreenHandler screenHandler, int mapX, int mapY){
        mapHandler = new MapHandler(mapX,mapY);
        mapHandler.nextLevel=true;
        mapHandler.level=1;
        mapHandler.generateMap();
        entityHandler = new EntityHandler();
        entityHandler.generateEnemies();

        layingItemHandler = new LayingItemHandler();
        //start timer
        keyPressHandler = new KeyPressHandler(screenHandler);
    }

    public static void initTestMap(int mapX, int mapY){
        mapHandler = new MapHandler(mapX,mapY);
        mapHandler.nextLevel=true;
        mapHandler.level=1;
    }
    public static MapHandler mapHandler;
    public static EntityHandler entityHandler;
    public static LayingItemHandler layingItemHandler;
    public static KeyPressHandler keyPressHandler;
}
