package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.utils.HandlersManager;

public class PlayerAttackEnemyEvent {
    public static void attackEnemy(Player player, Enemy entity){
        int playerDamage = player.getDamage();
        int enemyHealth = entity.getHealth();
        int enemyDefence = entity.getDefence();
        int takenDamage = playerDamage-enemyDefence;
        if (takenDamage<0) takenDamage=0;
        entity.setHealth(enemyHealth-takenDamage);
        if(entity.isDead()){
            HandlersManager.entityHandler.deleteEntity(entity);
        }

    }
}
