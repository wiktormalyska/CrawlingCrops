package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;

public class Item {
    public String name;
    public String display;
    public boolean isWearable;
    public Slot slot;
    public int health=0;
    public int damage=0;
    public int defence=0;
    public double critChance =0;
    public Item(String name, String display){
        this.name =name;
        this.display = display;
        this.isWearable = false;
        this.slot = null;
        this.damage = 0;
        this.defence = 0;
        this.health = 0;
        this.critChance = 0;
    }
    public Item(String name, String display, boolean isWearable, Slot slot, int damage, int defence, int health, double critChance){
        this.name =name;
        this.display = display;
        this.isWearable = isWearable;
        this.slot = slot;
        this.critChance = critChance;
        this.defence = defence;
        this.health = health;
        this.damage = damage;
    }
    public static Item getAsItem(ItemsList itemType){
        return new Item(itemType.name, itemType.display, itemType.isWearable, itemType.slot, itemType.damage, itemType.defence, itemType.health, itemType.critChance);
    }
}
