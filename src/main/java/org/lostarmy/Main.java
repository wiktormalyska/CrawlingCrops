package org.lostarmy;

import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

public class Main {
    public static void main(String[] args) {
        int mapX = 10;
        int mapY = 10;
        int screenX = mapX+3;
        int screenY = 100;
        ScreenHandler screenHandler = new ScreenHandler(screenX, screenY, mapX, mapY);
        HandlersManager.init(screenHandler, mapX, mapY);



        //KeyPressHandler keyPressHandler = new KeyPressHandler();
    }



        //mapHandler.saveToFile("resources/map.txt");
}