package org.lostarmy.entities.EntityTypes.Player;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.items.laying.LayingItem;

public class Player extends Entity {

    public Player(int x, int y, String display, String name, int maxHealth, int damage) {
        super(x, y, display, name, maxHealth, damage);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private Inventory inventory = new Inventory();


    public void addItemToInventory(LayingItem item){
        inventory.addItem(item);
    }
    public boolean isDead(){
        return this.getHealth()<=0;
    }
}
