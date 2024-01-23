package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class TextCell extends Cell{
    public TextCell(int x, int y, String display) {
        super(x, y, display);
    }
    public TextCell(int x, int y, String display, String color) {
        super(x, y, color+display+ ConsoleColors.RESET);
    }
}
