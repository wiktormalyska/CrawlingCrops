package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class CaveFloor extends Cell {
    private static final String display = ConsoleColors.BLACK_BOLD_BRIGHT+"#";
    public CaveFloor(int x, int y) {
        super(x, y, display);
    }

}
