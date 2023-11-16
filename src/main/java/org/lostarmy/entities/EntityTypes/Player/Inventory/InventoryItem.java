package org.lostarmy.entities.EntityTypes.Player.Inventory;

import org.lostarmy.items.Item;

public class InventoryItem {
    public InventoryItem(Item item) {
        this.item = item;
    }
    public int amount = 0;
    public Item item;
}
