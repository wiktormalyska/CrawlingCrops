package org.lostarmy.utils;

import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.map.MapHandler;
import org.lostarmy.screen.ScreenHandler;

public class HandlersManager {
    public HandlersManager(ScreenHandler screenHandler, int mapX, int mapY){
        mapHandler = new MapHandler(mapX,mapY, screenHandler);
        entityHandler = new EntityHandler();
        layingItemHandler = new LayingItemHandler();
        keyPressHandler = new KeyPressHandler(screenHandler);
    }
    public static MapHandler mapHandler;
    public static EntityHandler entityHandler;
    public static LayingItemHandler layingItemHandler;
    public static KeyPressHandler keyPressHandler;
}
