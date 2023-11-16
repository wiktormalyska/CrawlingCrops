package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.items.laying.LayingItem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<InventoryItem> items = new ArrayList<>();
    public String inventoryDisplay = "---Inventory---";
    public void addItem(LayingItem item){
        if (items.contains(item)){
            for (InventoryItem inventoryItem:items){
                if (inventoryItem.equals(item)){
                    inventoryItem.amount++;
                    return;
                }
            }
        }
        InventoryItem inventoryItem = new InventoryItem(item.item);
        inventoryItem.amount++;
        items.add(inventoryItem);
    }
    public void removeItem(LayingItem item){
        items.remove(item);
    }
}
