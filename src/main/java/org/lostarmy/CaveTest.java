package org.lostarmy;

import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import static org.lostarmy.utils.HandlersManager.mapHandler;

public class CaveTest {
    public static void main(String[] args) {
        int mapX = 50;
        int mapY = 50;
        int screenX = mapX+3;
        int screenY = 100;
        ScreenHandler screenHandler = new ScreenHandler(screenX, screenY, mapX, mapY);
        HandlersManager.initTestMap(mapX, mapY, screenHandler);

        mapHandler.generateCaveMap();
        printMap(mapX, mapY);
    }

    public static void printMap(int mapX, int mapY) {
        for (int i = 0; i < mapX+2; i++) {
            for (int j = 0; j < mapY+2; j++) {
                System.out.print(mapHandler.getCell(i, j).getDisplay()+ ConsoleColors.RESET);
            }
            System.out.println();
        }
    }



        //mapHandler.saveToFile("resources/map.txt");
}
