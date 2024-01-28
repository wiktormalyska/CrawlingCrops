package org.lostarmy.events;

import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.laying.LayingItem;
import org.lostarmy.utils.HandlersManager;

public class PlayerPickupItemEvent extends Event {
    public PlayerPickupItemEvent() {
        super();
    }

    public static void pickupItem(Player player, LayingItem item) {
        HandlersManager.layingItemHandler.itemsOnGround.remove(item);
        player.addItemToInventory(item);
    }
}
