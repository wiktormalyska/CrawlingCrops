package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.map.CellTypes.Cell;

import org.lostarmy.utils.HandlersManager;

public class NextLevelEvent extends Event {
    public static void nextLevel() {
        HandlersManager.mapHandler.level++;
        if (HandlersManager.mapHandler.level == 5) {
            System.out.println("You won!");
            System.exit(0);
        }
        HandlersManager.entityHandler.clearEnemies();
        HandlersManager.mapHandler.nextLevel = true;
        HandlersManager.mapHandler.generateMap();
        HandlersManager.entityHandler.generateEnemies();
        HandlersManager.layingItemHandler.generateItems();
        HandlersManager.mapHandler.generateLayingItems();
        Player player = HandlersManager.entityHandler.getPlayer();
        Cell pp = HandlersManager.mapHandler.findClosestFreeCell(HandlersManager.mapHandler.getMap());
        player.setX(pp.getX());
        player.setY(pp.getY());
    }
}
