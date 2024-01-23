package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;

public class Item {
    public final String name;
    public final String display;
    public final boolean isWearable;
    public final Slot slot;

    public Item(String name, String display){
        this.name =name;
        this.display = display;
        this.isWearable = false;
        this.slot = null;
    }
    public static Item getAsItem(ItemsList itemType){
        return new Item(itemType.name, itemType.display);
    }
    public static Item getAsItem(ArmorList itemType){
        return new Armor(itemType.name, itemType.display, itemType.health, itemType.damage, itemType.defence, itemType.critChance, itemType.slot);
    }
    public static Item getAsItem(FoodList food){
        return new Food(food.name, food.display,food.regenHealth);
    }
}
