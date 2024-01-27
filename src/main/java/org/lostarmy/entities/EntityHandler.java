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
        switch (HandlersManager.mapHandler.level) {
            case 1 -> {
                for (int i = 0; i < Math.random() * 10 + 1; i++) {
                    enemiesTypes.add(EnemyType.RAT);
                }
                for (int i = 0; i < Math.random() * 4 + 1; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
            }
            case 2 -> {
                for (int i = 0; i < Math.random() * 10 + 1; i++) {
                    enemiesTypes.add(EnemyType.RAT);
                }
                for (int i = 0; i < Math.random() * 2 + 1; i++) {
                    enemiesTypes.add(EnemyType.WOLF);
                }
                for (int i = 0; i < Math.random() * 1 + 1; i++) {
                    enemiesTypes.add(EnemyType.BEAR);
                }
            }
        }
        MapHandler mapHandler = HandlersManager.mapHandler;
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

    private final List<Entity> entities = new ArrayList<>();

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y) return enemy;
        }
        return null;
    }

    private final List<Enemy> enemies = new ArrayList<>();
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public EntityHandler() {
        MapHandler maph = HandlersManager.mapHandler;
        Cell center = maph.findClosestToCenter();
        this.player = new Player(center.getX(), center.getY(), ConsoleColors.PURPLE_BOLD_BRIGHT + "@", "Player", 100, 20, 6, 0.25);
        entities.add(player);
    }

    public EntityHandler(boolean test) {
        if (test) {
            MapHandler maph = HandlersManager.mapHandler;
            Cell center = maph.findClosestToCenter();
            this.player = new Player(center.getX(), center.getY(), ConsoleColors.PURPLE_BOLD_BRIGHT + "@", "Player", 100, 20, 6, 0.25);
            entities.add(player);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void deleteEntity(Entity entity) {
        entities.remove(entity);
        if (entity instanceof Enemy) {
            enemies.remove(entity);
        }
    }

    public void addEnemy(Enemy enemy) {
        entities.add(enemy);
        enemies.add(enemy);
    }
}
