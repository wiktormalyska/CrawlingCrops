package org.lostarmy.items;

public class Food extends Item{
    public int regenHealth;
    public Food(String name, String display, int regenHealth) {
        super(name, display);
        this.regenHealth = regenHealth;
    }
    public Food(FoodList foodList){
        super(foodList.name, foodList.display);
        this.regenHealth = foodList.regenHealth;
    }

}
