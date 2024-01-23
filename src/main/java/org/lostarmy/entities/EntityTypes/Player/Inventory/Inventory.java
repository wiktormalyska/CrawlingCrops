package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.entities.EntityTypes.Entity;
import org.lostarmy.items.Armor;
import org.lostarmy.items.Item;
import org.lostarmy.items.laying.LayingItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public Armor head;
    public Armor chest;
    public Armor legs;
    public Armor boots;
    public Armor weapon;
    public final List<InventoryItem> backpack = new ArrayList<>();
    public final String inventoryDisplay = "--Equipped--";
    public List<Armor> getWornItems(){
        List<Armor> items = new ArrayList<>();
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
    public void addItem(LayingItem layingItem){
        Item item = layingItem.item;
        for (InventoryItem inventoryItem:backpack){
            if (inventoryItem.item.name.equals(item.name)){
                inventoryItem.amount++;
                return;
            }
        }
        InventoryItem inventoryItem = new InventoryItem(item);
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
    public void wearItem(Armor item, Entity entity){
        boolean worn = false;
        switch (item.slot){
            case HEAD:
                if (head != null){
                    unwearItem(head, entity);
                    break;
                }
                head = item;
                worn = true;
                break;
            case CHEST:
                if (chest != null){
                    unwearItem(chest, entity);
                    break;
                }
                chest = item;
                worn = true;
                break;
            case LEGS:
                if (legs != null){
                    unwearItem(legs, entity);
                    break;
                }
                legs = item;
                worn = true;
                break;
            case BOOTS:
                if (boots != null){
                    unwearItem(boots, entity);
                    break;
                }
                boots = item;
                worn = true;
                break;
            case WEAPON:
                if (weapon != null){
                    unwearItem(weapon, entity);
                    break;
                }
                weapon = item;
                worn = true;
                break;
        }
        applyWornItemsEffects(entity);
        if (worn){
            System.out.println("Equipped: "+item.name+" to "+item.slot.name()+" slot");
        } else {
            System.out.println("Unequipped: "+item.name+" from "+item.slot.name()+" slot");
        }
    }
    public void unwearItem(Armor item, Entity entity){
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
