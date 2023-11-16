package org.lostarmy.game;

import org.lostarmy.events.Event;

public class TurnHandler {
    public static int turn = 0;

    public TurnHandler() {
        turn = 0;
    }

    public static void nextTurn(Event event) {
        turn++;
    }
}
