package org.lostarmy.items;

public class Food extends Item{
    public int regenHealth=0;
    public Food(String name, String display, int regenHealth) {
        super(name, display);
        this.regenHealth = regenHealth;
    }

}
