package org.lostarmy.items;

public enum ItemsList {
    STICK();
    public final String name;
    public final String display;

    ItemsList() {
        this.name = "Stick";
        this.display = "\u001B[1;33m/";
    }

}
