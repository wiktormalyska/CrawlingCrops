package org.lostarmy.entities.EntityTypes;

import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.MapHandler;

public abstract class Entity extends Cell {

    final String name;
    private int health;
    private final int maxHealth;

    private int damage;
    private int defence;

    private double critChance;

    public double getCritChance() {
    	return critChance;
    }

    public void setCritChance(double critChance) {
    	this.critChance = critChance;
    }

    public int getDefence(){
        return defence;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

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



    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public Entity(int x, int y, String display, String name, int maxHealth, int damage, int defence, double critChance) {
        super(x, y, display);
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
        this.defence = defence;
        this.critChance = critChance;
    }
}
