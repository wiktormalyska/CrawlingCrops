package org.lostarmy.map.CellTypes;

public class Border extends Cell {
    public Border(int x, int y, BorderType type) {
        super(x, y, type.display);
    }
}
