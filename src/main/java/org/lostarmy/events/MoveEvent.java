package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.utils.HandlersManager;

public class MoveEvent extends Event {
    public static void movePlayer(MoveTypes type, Player player) {
        switch (type){
            case UP -> {
                if (checkIfPlayerAttackEnemy(player.getX()-1, player.getY(), player))return;
                    int x = player.getX();
                    int y = player.getY();

                    player.moveTo(x-1, y);
            }
            case DOWN -> {
                if (checkIfPlayerAttackEnemy(player.getX()+1, player.getY(), player))return;
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x+1, y);
            }
            case LEFT -> {
                if (checkIfPlayerAttackEnemy(player.getX(), player.getY()-1, player))return;
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x , y-1);
            }
            case RIGHT -> {
                if (checkIfPlayerAttackEnemy(player.getX(), player.getY()+1, player))return;
                    int x = player.getX();
                    int y = player.getY();
                    player.moveTo(x, y+1);
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
    private static boolean checkIfPlayerAttackEnemy(int x, int y, Player player){
        for (Enemy enemy : HandlersManager.entityHandler.getEnemies()) {
            int entityX = enemy.getX();
            int entityY = enemy.getY();
            if (x == entityX && y == entityY){
                PlayerAttackEnemyEvent.attackEnemy(player, enemy);
                return true;
            }
        }
        return false;
    }
}
