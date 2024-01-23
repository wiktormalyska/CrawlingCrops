package org.lostarmy.entities.EntityTypes.Enemy;

import org.lostarmy.utils.ConsoleColors;

public enum EnemyKillChanceDisplay {
    IMPOSSIBLE(ConsoleColors.RED_BOLD_BRIGHT),
    MAYBE(ConsoleColors.YELLOW_BOLD_BRIGHT),
    POSSIBLE(ConsoleColors.GREEN_BOLD_BRIGHT);
    public final String colors;
    EnemyKillChanceDisplay(String colors){
        this.colors = colors;
    }
}
