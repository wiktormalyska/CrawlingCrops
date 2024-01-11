package org.lostarmy.items.laying;

import org.lostarmy.entities.EntityTypes.Player.Inventory.Slot;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;

import java.util.ArrayList;
import java.util.List;

public class LayingItemHandler {
    public static List<LayingItem> itemsOnGround = new ArrayList<>();
    public LayingItemHandler() {
        LayingItem stick = new LayingItem(3, 3, Item.getAsItem(ItemsList.STICK));
        LayingItem meat = new LayingItem(1, 1, Item.getAsItem(ItemsList.MEAT));
        LayingItem helmet = new LayingItem(4, 4, Item.getAsItem(ItemsList.HELMET));

        itemsOnGround.add(stick);
        itemsOnGround.add(meat);
        itemsOnGround.add(helmet);
    }

}
