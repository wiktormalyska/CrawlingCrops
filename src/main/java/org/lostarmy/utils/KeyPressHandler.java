package org.lostarmy.utils;

import org.lostarmy.ServerWebSocket;
import org.lostarmy.events.MoveEvent;
import org.lostarmy.events.MoveTypes;
import org.lostarmy.screen.InventoryScreen;
import org.lostarmy.screen.ScreenHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lostarmy.utils.HandlersManager.entityHandler;
import static org.lostarmy.utils.HandlersManager.mapHandler;

public class KeyPressHandler{
    private final ScreenHandler screenHandler;
    public KeyPressHandler(ScreenHandler screenHandler){
        this.screenHandler = screenHandler;
        boolean isServer = ScreenHandler.isServer;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //main game loop
            updateMap(screenHandler);
            while (!entityHandler.getPlayer().isDead()) {
                if (isServer){
                    String clientInput = screenHandler.clientHandler.readLine();
                    if (clientInput== null ||clientInput.isEmpty()) continue;
                    int key = clientInput.charAt(0);
                    keyPressed(key);
                } else {
                    String input = reader.readLine();
                    if (input.isEmpty()) continue;
                    int key = input.charAt(0);
                    keyPressed(key);
                }

                entityHandler.update();
            }

            if (isServer){
                ScreenHandler.clearDisplay(screenHandler.clientHandler);
                screenHandler.clientHandler.println("You died!");
            } else {
                ScreenHandler.clearDisplay();
                System.out.println("You died!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(int key) {
        switch (key){
            case 'w','W' -> {
                MoveEvent.movePlayer(MoveTypes.UP, entityHandler.getPlayer());
                updateMap(screenHandler);
            }
            case 's','S' -> {
                MoveEvent.movePlayer(MoveTypes.DOWN, entityHandler.getPlayer());
                updateMap(screenHandler);
            }
            case 'a','A' -> {
                MoveEvent.movePlayer(MoveTypes.LEFT, entityHandler.getPlayer());
                updateMap(screenHandler);
            }
            case 'd','D' -> {
                MoveEvent.movePlayer(MoveTypes.RIGHT, entityHandler.getPlayer());
                updateMap(screenHandler);
            }
            case 'f','F' -> {
                if (ScreenHandler.isServer){
                    screenHandler.clientHandler.println("You opened inventory");
                } else {
                    System.out.println("You opened inventory");
                }
                InventoryScreen inventoryScreen;
                if (screenHandler.clientHandler != null){
                    inventoryScreen = new InventoryScreen(screenHandler.screenCells.length, screenHandler.screenCells[0].length, screenHandler.mapX, screenHandler.mapY, screenHandler.clientHandler);
                } else {
                    inventoryScreen = new InventoryScreen(screenHandler.screenCells.length, screenHandler.screenCells[0].length, screenHandler.mapX, screenHandler.mapY);

                }
                inventoryScreen.openInventory();
                updateMap(screenHandler);
            }
        }
    }

    private static void updateMap(ScreenHandler screenHandler){
        mapHandler.generateMap();
        mapHandler.update();
        screenHandler.renderMap();
        screenHandler.print();
    }
}
