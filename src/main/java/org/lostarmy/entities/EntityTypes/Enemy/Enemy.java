package org.lostarmy.entities.EntityTypes.Enemy;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.events.EnemyAttackPlayerEvent;
import org.lostarmy.items.Item;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.utils.HandlersManager;

import java.util.List;

public class Enemy extends Entity {
    public Enemy(int x, int y, String display, String name, int baseHealth, List<Item> item, int damage, int defence, double critChance) {
        super(x, y, display, name, baseHealth, damage, defence, critChance);
        this.item= item;
    }
    public void update(){
        Player player = HandlersManager.entityHandler.getPlayer();
        double distance = Math.sqrt(Math.pow(player.getX()-this.getX(), 2) + Math.pow(player.getY()-this.getY(), 2));

        if (distance <= 3){
            int nextX = this.getX();
            int nextY = this.getY();

            if (player.getX() > this.getX()) {
                nextX++;
            } else if (player.getX() < this.getX()) {
                nextX--;
            }

            if (player.getY() > this.getY()) {
                nextY++;
            } else if (player.getY() < this.getY()) {
                nextY--;
            }

            // If the next position is the player's position, attack the player
                if (nextX == player.getX() && nextY == player.getY()) {
                EnemyAttackPlayerEvent.attackPlayer(player, this);
            }

            // If the next position is not the player's position, move the enemy
            if (nextX != player.getX() || nextY != player.getY()) {
                if (HandlersManager.entityHandler.getEnemyAt(nextX, nextY) == null) {
                    this.moveTo(nextX, nextY);
                }
            }

        }

    }
    private final List<Item> item;
    public LayingItem getDropItem(int x, int y) {

        return new LayingItem(x, y, item.get((int) (Math.random() * 2)));
    }
public String getEnemyHardness(){
    Player player = HandlersManager.entityHandler.getPlayer();
    int attacksNeeded = (int) Math.ceil((double) this.getHealth() / player.getDamage());

    if (attacksNeeded == 1){
        return EnemyKillChanceDisplay.POSSIBLE.colors; // Green
    } else if (attacksNeeded == 2) {
        return EnemyKillChanceDisplay.MAYBE.colors; // Yellow
    } else {
        return EnemyKillChanceDisplay.IMPOSSIBLE.colors; // Red
    }
}

    public boolean isDead(){
        return this.getHealth()<=0;
    }
}
