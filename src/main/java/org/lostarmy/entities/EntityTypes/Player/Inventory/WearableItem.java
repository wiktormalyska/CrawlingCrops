package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.items.Item;

public class WearableItem extends InventoryItem{
    public int health=0;
    public int damage=0;
    public int defence=0;
    public double critChance =0;
    public Slot slot;

    public WearableItem(Item item) {
        super(item);
        this.slot = item.slot;
        this.damage = item.damage;
        this.defence = item.defence;
        this.health = item.health;
        this.critChance = item.critChance;
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
