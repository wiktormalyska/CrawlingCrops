package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.utils.HandlersManager;

public class EnemyAttackPlayerEvent extends Event{
    public static void attackPlayer(Player player, Enemy entity){
        int playerHealth = player.getHealth();
        int enemyDamage = entity.getDamage();
        int playerDefence = player.getDefence();
        double enemyCritChance = entity.getCritChance();
        if (Math.random()<=enemyCritChance){
            enemyDamage*=1.5;
        }
        int takenDamage = enemyDamage-playerDefence;
        if (takenDamage<0) takenDamage=0;
        player.setHealth(playerHealth-takenDamage);
    }

}
