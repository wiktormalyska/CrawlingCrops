package org.lostarmy.items;

public enum FoodList {
    MEAT()
    ;
    public final String name;
    public final String display;
    public final int regenHealth;
    FoodList (){
        this.name = "Meat";
        this.display = "\u001B[1;35m8";
        this.regenHealth = 5;
    }

}
