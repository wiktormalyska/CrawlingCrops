package org.lostarmy.items;

public class Item {
    public String name;
    public String display;
    public Item(String name, String display){
        this.name =name;
        this.display = display;
    }
    public static Item getAsItem(ItemsList itemType){
        return new Item(itemType.name, itemType.display);
    }
}
