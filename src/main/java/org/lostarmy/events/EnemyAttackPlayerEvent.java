package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.utils.HandlersManager;

public class EnemyAttackPlayerEvent extends Event{
    public static void attackPlayer(Player player, Enemy entity){
        int playerHealth = player.getHealth();
        int enemyDamage = entity.getDamage();
        double enemyCritChance = entity.getCritChance();
        double playerDefenceCritChance = player.getCritChance(); // Assume this method exists in Player class
        int playerDefence = player.getDefence();

        // Check for enemy's critical hit
        if (Math.random() <= enemyCritChance) {
            enemyDamage = (int)(enemyDamage*1.5);
        }

        // Check for player's defence critical hit
        if (Math.random() <= playerDefenceCritChance) {
            playerDefence += 2; // Increase player's defence by 2
        }

        int takenDamage = enemyDamage - playerDefence;
        if (takenDamage < 0) takenDamage = 0;
        player.setHealth(playerHealth - takenDamage);
    }


}
