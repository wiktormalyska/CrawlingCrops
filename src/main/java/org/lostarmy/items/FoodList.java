package org.lostarmy.items;

import org.lostarmy.utils.ConsoleColors;

public enum FoodList {
    MEAT("Meat", ConsoleColors.PURPLE+"8", 5),
    ROTTEN_MEAT("Rotten Meat", ConsoleColors.GREEN+"8", 2),
    ;
    public final String name;
    public final String display;
    public final int regenHealth;
    FoodList (String name, String display, int regenHealth){
        this.name = name;
        this.display = display;
        this.regenHealth = regenHealth;
    }

}
