package org.lostarmy.items.laying;

import org.lostarmy.items.*;
import org.lostarmy.map.CellTypes.*;
import org.lostarmy.utils.HandlersManager;

import java.util.ArrayList;
import java.util.List;

public class LayingItemHandler {
    public List<LayingItem> itemsOnGround;
    private final List<Item> randomItemList;
    public LayingItemHandler() {
        itemsOnGround = new ArrayList<>();
        randomItemList = new ArrayList<>();
        addRandomItems();
    }
    private Item getRandomItem(){
        return randomItemList.get((int) (Math.random()*randomItemList.size()));
    }
    private void addRandomItems(){
        randomItemList.add(new Food(FoodList.MEAT));
        randomItemList.add(new Food(FoodList.ROTTEN_MEAT));

        randomItemList.add(new Armor(ArmorList.STEEL_BOOTS));
        randomItemList.add(new Armor(ArmorList.STEEL_CHESTPLATE));
        randomItemList.add(new Armor(ArmorList.STEEL_HELMET));
        randomItemList.add(new Armor(ArmorList.STEEL_LEGGINGS));
        randomItemList.add(new Armor(ArmorList.STEEL_SWORD));
    }

    public void generateItems(){
        Cell[][] map = HandlersManager.mapHandler.getMap();
        itemsOnGround.clear();
        int numItems = (int) (Math.random() * 5 + 1);
        for (int i = 0; i < numItems; i++) {
            int x = (int) (Math.random() * (map.length - 2) + 1);
            int y = (int) (Math.random() * (map[0].length - 2) + 1);
            while (HandlersManager.mapHandler.getCell(x, y) instanceof Wall || HandlersManager.mapHandler.getCell(x, y) instanceof Tree || HandlersManager.mapHandler.getCell(x, y) instanceof TrapDor || HandlersManager.mapHandler.getCell(x, y) instanceof Border) {
                x = (int) (Math.random() * (map.length - 2) + 1);
                y = (int) (Math.random() * (map[0].length - 2) + 1);
            }
            LayingItem item = new LayingItem(x, y , getRandomItem());
            itemsOnGround.add(item);
        }
    }
}
