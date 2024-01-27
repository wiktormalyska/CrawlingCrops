package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class TrapDor extends Cell{
    public TrapDor(int x, int y) {
        super(x, y, ConsoleColors.YELLOW_BRIGHT+"^");
    }
}
