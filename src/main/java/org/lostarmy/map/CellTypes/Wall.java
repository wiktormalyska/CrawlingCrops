package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class Wall extends Cell{
    public Wall(int x, int y) {
        super(x, y, ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLACK_BOLD_BRIGHT+"#"+ConsoleColors.RESET);
    }
}
