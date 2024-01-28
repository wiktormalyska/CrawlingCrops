package org.lostarmy;

import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

public class Main {
    public static void main(String[] args) {
        int mapX = 50;
        int mapY = 50;
        int screenX = 13;
        int screenY = 100;
        ScreenHandler screenHandler = new ScreenHandler(screenX, screenY);
        HandlersManager.init(screenHandler, mapX, mapY);

    }
}