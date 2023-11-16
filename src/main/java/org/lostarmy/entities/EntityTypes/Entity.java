package org.lostarmy.entities.EntityTypes;

import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.MapHandler;

public abstract class Entity extends Cell {

    final String name;
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void moveTo(int x, int y){
        if (x == 0 || x == MapHandler.mapSizeX+1 || y == 0 || y == MapHandler.mapSizeY+1)return;
        super.setY(y);
        super.setX(x);
    }

    private int maxHealth;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    private int damage;


    public Entity(int x, int y, String display, String name, int maxHealth, int damage) {
        super(x, y, display);
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
    }
}
