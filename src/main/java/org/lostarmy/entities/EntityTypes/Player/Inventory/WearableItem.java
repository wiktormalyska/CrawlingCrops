package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.entities.EntityTypes.Entity;

public class WearableItem {
    //TODO: dodać sloty
    public String name;
    public int health=0;
    public int damage=0;
    public int defence=0;
    public double critChance =0;
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
