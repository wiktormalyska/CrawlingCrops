package org.lostarmy.items;
import org.lostarmy.ClientHandler;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

import java.net.Socket;

public interface Use {
    default void useItem(Item item, int index, ClientHandler clientHandler){
        if (item instanceof Food food){
            eat(food, index, clientHandler);
        }
        if (item instanceof Armor armor) {
            wear(armor);
        }
    }
    default void wear(Armor armor){
        Player player = HandlersManager.entityHandler.getPlayer();
        Inventory inventory = player.getInventory();
        inventory.wearItem(armor, player);
    }
    default void eat(Food food, int index, ClientHandler clientHandler){
        Player player = HandlersManager.entityHandler.getPlayer();
        Inventory inventory = player.getInventory();
        if (player.getMaxHealth() == player.getHealth()) {
            if(ScreenHandler.isServer){
                clientHandler.println("You are already at full health!");
            } else {
                System.out.println("You are already at full health!");
            }
            return;
        }
        inventory.backpack.get(index).amount--;
        if (inventory.backpack.get(index).amount == 0){
            inventory.backpack.remove(index);
        }
        player.setHealth(player.getHealth() + food.regenHealth);
        if (player.getMaxHealth()<player.getHealth()) {
                player.setHealth(player.getMaxHealth());
        }
        if (ScreenHandler.isServer)
            clientHandler.println("You ate "+food.name+" and gained "+food.regenHealth+" health!");
        else
            System.out.println("You ate "+food.name+" and gained "+food.regenHealth+" health!");

    }
}
