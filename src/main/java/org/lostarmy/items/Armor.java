package org.lostarmy.items;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;

public class Armor extends Item{
    public int health;
    public int damage;
    public int defence;
    public double critChance;
    public final Slot slot;
    public Armor(String name, String display, int health, int damage, int defence, double critChance, Slot slot) {
        super(name, display);
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.critChance = critChance;
        this.slot=slot;
    }
    public Armor(ArmorList armorList){
        super(armorList.name, armorList.display);
        this.health = armorList.health;
        this.damage = armorList.damage;
        this.defence = armorList.defence;
        this.critChance = armorList.critChance;
        this.slot=armorList.slot;
    }
    public void applyEffects(Entity entity) {
        entity.setHealth(entity.getHealth()+health);
        entity.setDamage(entity.getDamage()+damage);
        entity.setDefence(entity.getDefence()+defence);
        entity.setCritChance(entity.getCritChance()+ critChance);
    }
    public void removeEffects(Entity entity) {
        entity.setHealth(entity.getHealth()-health);
        entity.setDamage(entity.getDamage()-damage);
        entity.setDefence(entity.getDefence()-defence);
        entity.setCritChance(entity.getCritChance()- critChance);
    }
}
