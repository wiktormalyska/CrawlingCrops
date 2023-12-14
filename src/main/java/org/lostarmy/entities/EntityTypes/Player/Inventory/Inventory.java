package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.items.laying.LayingItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public WearableItem head;
    public WearableItem chest;
    public WearableItem legs;
    public WearableItem boots;
    public WearableItem weapon;
    public List<InventoryItem> backpack = new ArrayList<>();
    public String inventoryDisplay = "---Inventory---";
    public void addItem(LayingItem item){
        for (InventoryItem inventoryItem:backpack){
            if (inventoryItem.item.name.equals(item.item.name)){
                inventoryItem.amount++;
                return;
            }
        }
        InventoryItem inventoryItem = new InventoryItem(item.item);
        inventoryItem.amount++;
        backpack.add(inventoryItem);
    }
    public void applyWornItemsEffects(Entity entity){
        if (head != null){
            head.applyEffects(entity);
        }
        if (chest != null){
            chest.applyEffects(entity);
        }
        if (legs != null){
            legs.applyEffects(entity);
        }
        if (boots != null){
            boots.applyEffects(entity);
        }
        if (weapon != null){
            weapon.applyEffects(entity);
        }
    }

    public void removeItem(LayingItem item){
        backpack.remove(item);
    }
    public void wearItem(WearableItem item, Entity entity){
        //TODO: dodać sloty
    }
    public void unwearItem(WearableItem item, Entity entity){
        if (item == head){
            head.removeEffects(entity);
            head = null;
        } else if (item == chest){
            chest.removeEffects(entity);
            chest = null;
        } else if (item == legs){
            legs.removeEffects(entity);
            legs = null;
        } else if (item == boots){
            boots.removeEffects(entity);
            boots = null;
        } else if (item == weapon){
            weapon.removeEffects(entity);
            weapon = null;
        }
    }
}
