package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public class Grass extends Cell {
    private static String display = ConsoleColors.GREEN_BACKGROUND+ConsoleColors.GREEN_BOLD_BRIGHT +"#";
    public Grass(int x, int y) {
        super(x, y, display);
    }
}
