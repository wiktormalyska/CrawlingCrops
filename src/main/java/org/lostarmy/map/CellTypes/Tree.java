package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class Tree extends Cell{
    public Tree(int x, int y) {
        super(x, y, ConsoleColors.YELLOW_BOLD_BRIGHT+"#");
    }
}
