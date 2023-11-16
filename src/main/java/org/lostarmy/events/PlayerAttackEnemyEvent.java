package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.utils.HandlersManager;

public class PlayerAttackEnemyEvent {
    public static void attackEnemy(Player player, Enemy entity){
        int playerDamage = player.getDamage();
        int enemyHealth = entity.getHealth();
        entity.setHealth(enemyHealth-playerDamage);
        int enemyDamage = entity.getDamage();
        int playerHealth = player.getHealth();
        player.setHealth(playerHealth-enemyDamage);
        if(entity.isDead()){
            HandlersManager.entityHandler.deleteEntity(entity);
        }

    }
}
