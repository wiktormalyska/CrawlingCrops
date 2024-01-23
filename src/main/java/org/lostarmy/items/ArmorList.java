package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;

public enum ArmorList {
    HELMET();
    public final String name;
    public final String display;
    public int health=0;
    public int damage=0;
    public int defence=0;
    public double critChance =0;
    public final Slot slot;
    ArmorList(){
        this.name = "Helmet";
        this.display = "\u001B[1;37mH";
        this.health = 0;
        this.damage = 0;
        this.defence = 1;
        this.critChance = 0;
        this.slot = Slot.HEAD;
    }
}
