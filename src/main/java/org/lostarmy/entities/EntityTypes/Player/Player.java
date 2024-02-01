package org.lostarmy.entities.EntityTypes.Player;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.utils.ConsoleColors;

public class Player extends Entity {

    public Player(int x, int y, String display, String name, int baseHealth, int damage, int defence, double critChance) {
        super(x, y, display, name, baseHealth, damage, defence, critChance);
    }

    public static Player genPlayer(int x, int y){
        return new Player(x, y, ConsoleColors.PURPLE_BOLD_BRIGHT+"@", "Player", 20, 8, 5, 0.25);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private final Inventory inventory = new Inventory();


    public void addItemToInventory(LayingItem item){
        inventory.addItem(item);
    }
    public boolean isDead(){
        return this.getHealth()<=0;
    }
}
