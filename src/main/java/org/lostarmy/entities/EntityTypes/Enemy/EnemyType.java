package org.lostarmy.entities.EntityTypes.Enemy;

import org.lostarmy.items.FoodList;
import org.lostarmy.items.Item;

import java.util.List;

public enum EnemyType {
    RAT, WOLF, HOG, BEAR, HYDRA_BEAR;

    public static Enemy getEnemyByType(EnemyType type, int x, int y){
        return type.getEnemy(x, y);
    }
    public Enemy getEnemy(int x, int y){
        switch (this) {
            case RAT -> {
                return new Enemy(x, y, "R", "Rat", 10, List.of(Item.getAsItem(FoodList.MEAT), Item.getAsItem(FoodList.ROTTEN_MEAT)), 3, 1, 0.2);
            }
            case WOLF -> {
                return new Enemy(x, y, "W", "Wolf", 40, List.of(Item.getAsItem(FoodList.MEAT), Item.getAsItem(FoodList.ROTTEN_MEAT)), 7, 6, 0.25);
            }
            case HOG -> {
                return new Enemy(x, y, "H", "Hog", 40, List.of(Item.getAsItem(FoodList.MEAT), Item.getAsItem(FoodList.ROTTEN_MEAT)), 7, 6, 0.4);
            }
            case BEAR -> {
                return new Enemy(x, y, "B", "Bear", 40, List.of(Item.getAsItem(FoodList.MEAT), Item.getAsItem(FoodList.ROTTEN_MEAT)), 8, 5, 0.35);
            }
            case HYDRA_BEAR -> {
                return new Enemy(x, y, "&", "Hydra Bear", 100, List.of(Item.getAsItem(FoodList.MEAT), Item.getAsItem(FoodList.ROTTEN_MEAT)), 10, 6, 0.4);
            }
        }
        return null;
    }
}