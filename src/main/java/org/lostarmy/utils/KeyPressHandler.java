package org.lostarmy.utils;

import org.lostarmy.events.MoveEvent;
import org.lostarmy.events.MoveTypes;
import org.lostarmy.screen.ScreenHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lostarmy.utils.HandlersManager.entityHandler;
import static org.lostarmy.utils.HandlersManager.mapHandler;

public class KeyPressHandler{
    private ScreenHandler screenHandler;
    public KeyPressHandler(ScreenHandler screenHandler){
        this.screenHandler = screenHandler;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if(entityHandler.getPlayer().isDead()){
                    break;
                }
                String input = reader.readLine();
                if (input.length() == 0) continue;
                int key = input.charAt(0);
                keyPressed(key);
            }
            System.out.println("You died!");
            //TODO: show death screen
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
        }
    }

    private static void updateMap(ScreenHandler screenHandler){
        mapHandler.generateMap();
        mapHandler.update(entityHandler);
        mapHandler.render();
        screenHandler.print();
    }
}
