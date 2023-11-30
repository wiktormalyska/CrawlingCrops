package org.lostarmy;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.events.EnemyAttackPlayerEvent;
import org.lostarmy.events.PlayerAttackEnemyEvent;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;

public class Test {
    public static void main(String[] args) {
        Player player = new Player(0, 0, "P", "Player", 100, 10, 5, 0.1);
        Enemy enemy = new Enemy(0, 0, "E", "Enemy", 100, Item.getAsItem(ItemsList.MEAT), 10, 5, 0.1);
        int tury=0;
        while (true) {
            try {
                if (player.getHealth()<=0) {
                    System.out.println("Player is dead");
                    break;
                }
                if (enemy.getHealth()<=0) {
                    System.out.println("Enemy is dead");
                    break;
                }
                tury++;
                System.out.println("Player health: " + player.getHealth());
                System.out.println("Enemy health: " + enemy.getHealth());
                PlayerAttackEnemyEvent.attackEnemy(player, enemy);
                EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                System.out.println();
            } catch (NullPointerException e){
                System.out.println("End");
            }
        }
        System.out.println(tury);
    }
}
