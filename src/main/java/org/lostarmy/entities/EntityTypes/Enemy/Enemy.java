package org.lostarmy.entities.EntityTypes.Enemy;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.Item;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.utils.HandlersManager;

public class Enemy extends Entity {
    public Enemy(int x, int y, String display, String name, int baseHealth, Item item, int damage, int defence, double critChance) {
        super(x, y, display, name, baseHealth, damage, defence, critChance);
        this.item= item;
    }
    private Item item;
    public LayingItem getDropItem(int x, int y) {
        return new LayingItem(x, y, item);
    }
    public String getEnemyHardness(){
        Player player = HandlersManager.entityHandler.getPlayer();
        if (player.getHealth()>this.getDamage() && player.getDamage()>=this.getHealth()){
            return EnemyKillChanceDisplay.POSSIBLE.colors;
        } else if (player.getHealth()>this.getDamage()) {
            return EnemyKillChanceDisplay.MAYBE.colors;
        } else if (player.getHealth()<=this.getDamage()){
            return EnemyKillChanceDisplay.IMPOSSIBLE.colors;
        }
        return "";
    }

    public boolean isDead(){
        return this.getHealth()<=0;
    }
}
