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
    public String inventoryDisplay = "--Equipped--";
    public List<WearableItem> getWornItems(){
        List<WearableItem> items = new ArrayList<>();
        if (head != null){
            items.add(head);
        }
        if (chest != null){
            items.add(chest);
        }
        if (legs != null){
            items.add(legs);
        }
        if (boots != null){
            items.add(boots);
        }
        if (weapon != null){
            items.add(weapon);
        }
        return items;
    }
    public void addItem(LayingItem item){
        for (InventoryItem inventoryItem:backpack){
            if (inventoryItem.item.name.equals(item.item.name)){
                inventoryItem.amount++;
                return;
            }
        }
        InventoryItem inventoryItem = new InventoryItem(item.item);
        if (item.item.isWearable){
            inventoryItem = new WearableItem(item.item);
        }
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
    public void wearItem(InventoryItem item, Entity entity){
        boolean worn = false;
        if (!(item instanceof WearableItem)){
            return;
        }
        switch (item.item.slot){
            case HEAD:
                if (head != null){
                    unwearItem(head, entity);
                    break;
                }
                head = new WearableItem(item.item);
                worn = true;
                break;
            case CHEST:
                if (chest != null){
                    unwearItem(chest, entity);
                    break;
                }
                chest = new WearableItem(item.item);
                worn = true;
                break;
            case LEGS:
                if (legs != null){
                    unwearItem(legs, entity);
                    break;
                }
                legs = new WearableItem(item.item);
                worn = true;
                break;
            case BOOTS:
                if (boots != null){
                    unwearItem(boots, entity);
                    break;
                }
                boots = new WearableItem(item.item);
                worn = true;
                break;
            case WEAPON:
                if (weapon != null){
                    unwearItem(weapon, entity);
                    break;
                }
                weapon = new WearableItem(item.item);
                worn = true;
                break;
        }
        applyWornItemsEffects(entity);
        if (worn){
            System.out.println("Equipped: "+item.item.name+" to "+item.item.slot.name()+" slot");
        } else {
            System.out.println("Unequipped: "+item.item.name+" from "+item.item.slot.name()+" slot");
        }
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
