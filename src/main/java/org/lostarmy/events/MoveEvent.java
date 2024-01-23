package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.utils.HandlersManager;

import java.util.ArrayList;
import java.util.List;

public class MoveEvent extends Event {
    public static void movePlayer(MoveTypes type, Player player) {
        switch (type){
            case UP -> {
                if (checkIfPlayerAttackEnemy(player.getX()-1, player.getY())) {
                    PlayerAttackEnemyEvent.attackEnemy(player, HandlersManager.entityHandler.getEnemyAt(player.getX()-1, player.getY()));
                    if (checkIfEnemyAttackPlayer(player)){
                        List<Enemy> enemies = getEnemiesAttackingPlayer(player);
                        for (Enemy enemy : enemies) {
                            EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                        }
                    }
                } else {
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x - 1, y);
                }
            }
            case DOWN -> {
                if (checkIfPlayerAttackEnemy(player.getX()+1, player.getY())) {
                    PlayerAttackEnemyEvent.attackEnemy(player, HandlersManager.entityHandler.getEnemyAt(player.getX()+1, player.getY()));
                    if (checkIfEnemyAttackPlayer(player)){
                        List<Enemy> enemies = getEnemiesAttackingPlayer(player);
                        for (Enemy enemy : enemies) {
                            EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                        }
                    }
                } else {
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x + 1, y);
                }
            }
            case LEFT -> {
                if (checkIfPlayerAttackEnemy(player.getX(), player.getY()-1)) {
                    PlayerAttackEnemyEvent.attackEnemy(player, HandlersManager.entityHandler.getEnemyAt(player.getX(), player.getY()-1));
                    if (checkIfEnemyAttackPlayer(player)){
                        List<Enemy> enemies = getEnemiesAttackingPlayer(player);
                        for (Enemy enemy : enemies) {
                            EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                        }
                    }
                } else {
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x, y - 1);
                }
            }
            case RIGHT -> {
                if (checkIfPlayerAttackEnemy(player.getX(), player.getY()+1)) {
                    PlayerAttackEnemyEvent.attackEnemy(player, HandlersManager.entityHandler.getEnemyAt(player.getX(), player.getY()+1));
                    if (checkIfEnemyAttackPlayer(player)){
                        List<Enemy> enemies = getEnemiesAttackingPlayer(player);
                        for (Enemy enemy : enemies) {
                            EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                        }
                    }
                } else {
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x, y + 1);
                }
            }
        }
        checkIfPlayerPickupItem(player);
    }
    private static void checkIfPlayerPickupItem(Player player){
        LayingItem pickedUpItem = null;
        for (LayingItem item : LayingItemHandler.itemsOnGround) {
            if (player.getX() == item.getX() && player.getY() == item.getY()) {
                pickedUpItem = item;
            }
        }
        if (pickedUpItem!=null){
            PlayerPickupItemEvent.pickupItem(player, pickedUpItem);
        }
    }
    private static boolean checkIfPlayerAttackEnemy(int x, int y){
        for (Enemy enemy : HandlersManager.entityHandler.getEnemies()) {
            int entityX = enemy.getX();
            int entityY = enemy.getY();
            if (x == entityX && y == entityY){
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfEnemyAttackPlayer(Player player){
        Enemy enemy1 = HandlersManager.entityHandler.getEnemyAt(player.getX(),player.getY()-1);
        Enemy enemy2 = HandlersManager.entityHandler.getEnemyAt(player.getX(),player.getY()+1);
        Enemy enemy3 = HandlersManager.entityHandler.getEnemyAt(player.getX()-1,player.getY());
        Enemy enemy4 = HandlersManager.entityHandler.getEnemyAt(player.getX()+1,player.getY());
        return enemy1 != null || enemy2 != null || enemy3 != null || enemy4 != null;
    }

    private static List<Enemy> getEnemiesAttackingPlayer(Player player){
        List<Enemy> enemies = new ArrayList<>();
        Enemy enemy1 = HandlersManager.entityHandler.getEnemyAt(player.getX(),player.getY()-1);
        Enemy enemy2 = HandlersManager.entityHandler.getEnemyAt(player.getX(),player.getY()+1);
        Enemy enemy3 = HandlersManager.entityHandler.getEnemyAt(player.getX()-1,player.getY());
        Enemy enemy4 = HandlersManager.entityHandler.getEnemyAt(player.getX()+1,player.getY());
        if (enemy1!=null) enemies.add(enemy1);
        if (enemy2!=null) enemies.add(enemy2);
        if (enemy3!=null) enemies.add(enemy3);
        if (enemy4!=null) enemies.add(enemy4);
        return enemies;
    }
}
