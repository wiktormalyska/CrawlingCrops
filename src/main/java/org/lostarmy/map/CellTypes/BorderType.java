package org.lostarmy.map.CellTypes;

import org.lostarmy.utils.ConsoleColors;

public enum BorderType {
    TOP(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'-'),
    BOTTOM(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'-'),
    LEFT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'|'),
    RIGHT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'|'),
    TOP_LEFT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'o'),
    TOP_RIGHT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'o'),
    BOTTOM_LEFT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'o'),
    BOTTOM_RIGHT(ConsoleColors.BLACK_BACKGROUND_BRIGHT+ConsoleColors.BLUE_BOLD_BRIGHT +'o');
    public final String display;
    BorderType(String display) {
        this.display = display;
    }

}
