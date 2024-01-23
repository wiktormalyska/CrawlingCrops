package org.lostarmy.items;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.utils.HandlersManager;

public interface Use {
    default void useItem(Item item, int index){
        if (item instanceof Food food){
            eat(food, index);
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
    default void eat(Food food, int index){
        Player player = HandlersManager.entityHandler.getPlayer();
        Inventory inventory = player.getInventory();
        if (player.getMaxHealth() == player.getHealth()) {
            System.out.println("You are already at full health!");
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
        System.out.println("You ate "+food.name+" and gained "+food.regenHealth+" health!");

    }
}
