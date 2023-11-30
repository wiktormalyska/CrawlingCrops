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
        int enemyHealth = entity.getHealth();
        int enemyDefence = entity.getDefence();
        if (Math.random()<=playerCritChance){
            playerDamage*=1.5;
        }
        int takenDamage = playerDamage-enemyDefence;
        if (takenDamage<0) takenDamage=0;
        entity.setHealth(enemyHealth-takenDamage);
        if(entity.isDead()){
            LayingItem drop = entity.getDropItem(entity.getX(), entity.getY());
            HandlersManager.entityHandler.deleteEntity(entity);
            LayingItemHandler.itemsOnGround.add(drop);
        }

    }
}
