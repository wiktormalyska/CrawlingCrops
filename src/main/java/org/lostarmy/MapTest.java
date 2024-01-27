package org.lostarmy;

import org.lostarmy.entities.EntityHandler;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.lostarmy.utils.HandlersManager.mapHandler;

public class MapTest {
    public static void main(String[] args) throws IOException {
        int mapX = 50;
        int mapY = 50;
        HandlersManager.initTestMap(mapX, mapY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = "1";
        while (input.charAt(0) != 'x') {
            input = reader.readLine();
            if (input.isEmpty()) continue;
            int key = input.charAt(0)-48;
            HandlersManager.mapHandler.level=key;
            switch (HandlersManager.mapHandler.level) {
                case 1 -> {
                    HandlersManager.mapHandler.generateForestMap();
                }
                case 2 -> {
                    HandlersManager.mapHandler.generateCaveMap();
                }
            }
            HandlersManager.entityHandler = new EntityHandler(true);
            HandlersManager.entityHandler.generateEnemies();
            mapHandler.update();
            printMap(mapX, mapY);
        }

    }

    public static void printMap(int mapX, int mapY) {
        for (int i = 0; i < mapX + 2; i++) {
            for (int j = 0; j < mapY + 2; j++) {
                System.out.print(HandlersManager.mapHandler.getCell(i, j).getDisplay() + ConsoleColors.RESET);
            }
            System.out.println();
        }
    }


    //mapHandler.saveToFile("resources/map.txt");
}
