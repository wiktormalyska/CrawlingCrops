package org.lostarmy.items;

import org.lostarmy.utils.ConsoleColors;

public enum ItemsList {
    STICK("Stick", ConsoleColors.YELLOW_BOLD+"/"),
    MEAT("Meat", ConsoleColors.PURPLE_BOLD+"8")
    ;
    public String name;
    public String display;
    ItemsList(String name, String display){
        this.name = name;
        this.display = display;
    }

}
