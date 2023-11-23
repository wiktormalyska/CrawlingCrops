package org.lostarmy.entities;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;
import org.lostarmy.utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
    private List<Entity> entities = new ArrayList<>();

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(int x, int y){
        for (Enemy enemy : enemies){
            if (enemy.getX()==x && enemy.getY()==y) return enemy;
        }
        return null;
    }

    private List<Enemy> enemies = new ArrayList<>();
    private Player player;
    public Player getPlayer() {
        return player;
    }
    public EntityHandler() {
        player = new Player(7, 7, ConsoleColors.PURPLE_BOLD_BRIGHT+"@", "Player", 10, 10, 5);
        Enemy enemy = new Enemy(5, 5, "@", "Enemy", 10, Item.getAsItem(ItemsList.MEAT), 5, 5);
        Enemy enemy2 = new Enemy(5, 6, "@", "Enemy", 1, Item.getAsItem(ItemsList.MEAT), 10, 1);

        entities.add(player);
        entities.add(enemy);
        enemies.add(enemy);
        entities.add(enemy2);
        enemies.add(enemy2);
    }

    public List<Entity> getEntities() {
        return entities;
    }
    public void deleteEntity(Entity entity){
        entities.remove(entity);
        if (entity instanceof Enemy){
            enemies.remove(entity);
        }
    }
}
