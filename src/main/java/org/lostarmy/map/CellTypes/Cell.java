package org.lostarmy.map.CellTypes;

public abstract class Cell {
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDisplay() {
        return display;
    }

    private final String display;
    public Cell(int x, int y, String display){
        this.x = x;
        this.y = y;
        this.display = display;
    }
}
