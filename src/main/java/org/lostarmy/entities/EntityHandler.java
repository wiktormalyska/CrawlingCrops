package org.lostarmy.entities;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Enemy.EnemyType;
import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.map.CellTypes.*;
import org.lostarmy.map.MapHandler;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
    List<EnemyType> enemiesTypes = new ArrayList<>();

    public void generateEnemies() {
        enemiesTypes.clear();
        MapHandler mapHandler = HandlersManager.mapHandler;
        int freeSpaces = mapHandler.getNumberOfFreeSpaces();
        int numRats = freeSpaces / 120;
        int numWolves = freeSpaces / 160;
        int numBears = freeSpaces / 180;

        switch (mapHandler.level) {
            case 1 -> {
                for (int i = 0; i < numRats; i++) {
                    enemiesTypes.add(EnemyType.RAT);
                }
                for (int i = 0; i < numWolves; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
            }
            case 2 -> {
                for (int i = 0; i < numRats; i++) {
                    enemiesTypes.add(EnemyType.RAT);
                }
                for (int i = 0; i < numWolves; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
                for (int i = 0; i < numBears; i++) {
                    enemiesTypes.add(EnemyType.BEAR);
                }
            }
            case 3 -> {
                for (int i = 0; i < numWolves; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
                for (int i = 0; i < numBears; i++) {
                    enemiesTypes.add(EnemyType.BEAR);
                }
            }
            case 4 -> {
                for (int i = 0; i < numWolves+2; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
                for (int i = 0; i < numBears+1; i++) {
                    enemiesTypes.add(EnemyType.BEAR);
                }
            }
            case 5 -> {
                enemiesTypes.add(EnemyType.HYDRA_BEAR);
            }
        }

        for (EnemyType enemyType : enemiesTypes) {
            int x = (int) (Math.random() * (MapHandler.mapSizeX - 2) + 1);
            int y = (int) (Math.random() * (MapHandler.mapSizeY - 2) + 1);
            while (mapHandler.getCell(x, y) instanceof Wall || mapHandler.getCell(x, y) instanceof Tree || mapHandler.getCell(x, y) instanceof TrapDor || mapHandler.getCell(x, y) instanceof Border) {
                x = (int) (Math.random() * (MapHandler.mapSizeX - 2) + 1);
                y = (int) (Math.random() * (MapHandler.mapSizeY - 2) + 1);
            }
            Enemy enemy = EnemyType.getEnemyByType(enemyType, x, y);
            addEnemy(enemy);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void clearEnemies() {
        enemies.clear();
    }

    public Enemy getEnemyAt(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y) return enemy;
        }
        return null;
    }

    private final List<Enemy> enemies = new ArrayList<>();
    private final Player player;

    public Player getPlayer() {
        return player;
    }

    public EntityHandler() {
        MapHandler mapHandler = HandlersManager.mapHandler;
        Cell center = mapHandler.findClosestFreeCell(mapHandler.getMap());
        this.player = Player.genPlayer(center.getX(), center.getY());
    }

    public void deleteEntity(Entity entity) {
        if (entity instanceof Enemy) {
            enemies.remove(entity);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }
}
