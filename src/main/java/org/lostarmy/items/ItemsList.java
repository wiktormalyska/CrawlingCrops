package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;
import org.lostarmy.utils.ConsoleColors;

public enum ItemsList {
    STICK("Stick", ConsoleColors.YELLOW_BOLD+"/"),
    MEAT("Meat", ConsoleColors.PURPLE_BOLD+"8"),
    HELMET("Helmet", ConsoleColors.WHITE_BOLD+"H", true, Slot.HEAD, 0, 0, 2, 0 ),
    ;
    public String name;
    public String display;
    public int health=0;
    public int damage=0;
    public int defence=0;
    public double critChance =0;
    public boolean isWearable = false;
    public Slot slot = null;
    ItemsList(String name, String display){
        this.name = name;
        this.display = display;
    }
    ItemsList(String name, String display, boolean isWearable, Slot slot, int health, int damage, int defence, double critChance){
        this.name = name;
        this.display = display;
        this.isWearable = isWearable;
        this.slot = slot;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.critChance = critChance;
    }

}
