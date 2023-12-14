package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.items.laying.LayingItemHandler;
import org.lostarmy.utils.HandlersManager;

public class PlayerAttackEnemyEvent {
    public static void attackEnemy(Player player, Enemy entity){
        int playerDamage = player.getDamage();
        double playerCritChance = player.getCritChance();
        double enemyDefenceCritChance = entity.getCritChance(); // Assume this method exists in Enemy class
        int enemyHealth = entity.getHealth();
        int enemyDefence = entity.getDefence();

        // Check for player's critical hit
        if (Math.random() <= playerCritChance) {
            playerDamage *= 1.5;
        }

        // Check for enemy's defence critical hit
        if (Math.random() <= enemyDefenceCritChance) {
            enemyDefence+=2; // Assume addDefence method exists in Enemy class
        }

        int takenDamage = playerDamage - enemyDefence;
        if (takenDamage < 0) takenDamage = 0;
        entity.setHealth(enemyHealth - takenDamage);

        if(entity.isDead()){
            LayingItem drop = entity.getDropItem(entity.getX(), entity.getY());
            HandlersManager.entityHandler.deleteEntity(entity);
            LayingItemHandler.itemsOnGround.add(drop);
        }
    }

}
