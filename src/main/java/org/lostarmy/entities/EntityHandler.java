package org.lostarmy.entities;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Enemy.EnemyType;
import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {

    public void generateEnemies(){
        EnemyType.RAT.createInstance(5, 5, this);
        EnemyType.WOLF.createInstance(5,7, this);
        EnemyType.HOG.createInstance(7, 5, this);
        EnemyType.BEAR.createInstance(7,7, this);
        EnemyType.HYDRA_BEAR.createInstance(7,9, this);
    }
    private final List<Entity> entities = new ArrayList<>();

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemyAt(int x, int y){
        for (Enemy enemy : enemies){
            if (enemy.getX()==x && enemy.getY()==y) return enemy;
        }
        return null;
    }

    private final List<Enemy> enemies = new ArrayList<>();
    private final Player player;
    public Player getPlayer() {
        return player;
    }
    public EntityHandler() {
        player = new Player(2, 2, ConsoleColors.PURPLE_BOLD_BRIGHT+"@", "Player", 50, 10, 2, 0.25);
        entities.add(player);
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

    public void addEnemy(Enemy enemy){
        entities.add(enemy);
        enemies.add(enemy);
    }
}
