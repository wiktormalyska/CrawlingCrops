package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;
import org.lostarmy.utils.ConsoleColors;

public enum ArmorList {
    STEEL_HELMET("Steel Helmet", ConsoleColors.WHITE+"H", 0, 0, 2, 0, Slot.HEAD),
    STEEL_CHESTPLATE("Steel Chestplate", ConsoleColors.WHITE+"C", 0, 0, 3, 0, Slot.CHEST),
    STEEL_LEGGINGS("Steel Leggings", ConsoleColors.WHITE+"L", 0, 0, 2, 0, Slot.LEGS),
    STEEL_BOOTS("Steel Boots", ConsoleColors.WHITE+"B", 0, 0, 1, 0, Slot.BOOTS),
    STEEL_SWORD("Steel Sword", ConsoleColors.WHITE+"S", 0, 3, 0, 0.1, Slot.WEAPON),;
    public final String name;
    public final String display;
    public final int health;
    public final int damage;
    public final int defence;
    public final double critChance;
    public final Slot slot;
    ArmorList(String name, String display, int health, int damage, int defence, double critChance, Slot slot){
        this.name = name;
        this.display = display;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.critChance = critChance;
        this.slot = slot;

    }
}
