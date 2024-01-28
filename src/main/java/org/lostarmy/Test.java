package org.lostarmy;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Enemy.EnemyType;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.events.EnemyAttackPlayerEvent;
import org.lostarmy.events.PlayerAttackEnemyEvent;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;
import org.lostarmy.utils.ConsoleColors;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        testEnemy(EnemyType.RAT);
        testEnemy(EnemyType.WOLF);
        testEnemy(EnemyType.HOG);
        testEnemy(EnemyType.BEAR);
        testEnemy(EnemyType.HYDRA_BEAR);

    }
    public static void testEnemy(EnemyType type){
        int iloscTestow = 100000;
        int tury = 0;
        for (int i = 0; i < iloscTestow; i++) {
            Enemy enemy = EnemyType.getEnemyByType(type, 0, 0);
            Player player = Player.testPlayer();
            int rundy = 0;
            while (true) {
                rundy++;
                try {
                    if (player.getHealth() <= 0) {
                        //System.out.println("Player is dead");
                        break;
                    }
                    if (enemy.getHealth() <= 0) {
                        //System.out.println("Enemy is dead");
                        tury++;
                        break;
                    }
                    //System.out.println("Player health: " + player.getHealth());
                    //System.out.println("Enemy health: " + enemy.getHealth());
                    PlayerAttackEnemyEvent.attackEnemy(player, enemy);
                    EnemyAttackPlayerEvent.attackPlayer(player, enemy);
                    //System.out.println();
                } catch (NullPointerException e) {
                    //System.out.println("End");
                }
            }
            //System.out.println(rundy);

        }
        double percentage = (double) tury/iloscTestow*100;
        System.out.println(Objects.requireNonNull(type.getEnemy(0, 0)).getName()+" - "+percentage+"%");
    }
}
