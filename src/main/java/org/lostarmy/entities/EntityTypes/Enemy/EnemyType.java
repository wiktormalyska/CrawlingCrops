package org.lostarmy.entities.EntityTypes.Enemy;

import org.lostarmy.entities.EntityHandler;
import org.lostarmy.items.FoodList;
import org.lostarmy.items.Item;
import org.lostarmy.items.ItemsList;

public enum EnemyType {
    RAT, WOLF, HOG, BEAR, HYDRA_BEAR;

    public static Enemy getEnemyByType(EnemyType type, int x, int y){
        return type.getEnemy(x, y);
    }
    public Enemy getEnemy(int x, int y){
        switch (this) {
            case RAT -> {
                return new Enemy(x, y, "@", "Rat", 10, Item.getAsItem(FoodList.MEAT), 3, 3, 0.2);
            }
            case WOLF -> {
                return new Enemy(x, y, "@", "Wolf", 20, Item.getAsItem(FoodList.MEAT), 4, 3, 0.25);
            }
            case HOG -> {
                return new Enemy(x, y, "@", "Hog", 20, Item.getAsItem(FoodList.MEAT), 5, 2, 0.4);
            }
            case BEAR -> {
                return new Enemy(x, y, "@", "Bear", 30, Item.getAsItem(FoodList.MEAT), 5, 2, 0.35);
            }
            case HYDRA_BEAR -> {
                return new Enemy(x, y, "@", "Hydra Bear", 100, Item.getAsItem(FoodList.MEAT), 10, 5, 0.4);
            }
        }
        return null;
    }

    public void createInstance(int x, int y, EntityHandler eh) {
        eh.addEnemy(getEnemy(x, y));
    }
}
