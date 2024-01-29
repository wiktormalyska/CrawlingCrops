package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class Marker extends Cell{
    public String color;
    public Marker(int x, int y, String color) {
        super(x, y, color+"0"+ ConsoleColors.RESET);
        this.color=color;
    }
}
