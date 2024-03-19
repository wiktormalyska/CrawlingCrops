package org.lostarmy.screen;

import org.lostarmy.ClientHandler;
import org.lostarmy.ServerWebSocket;
import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.items.Armor;
import org.lostarmy.map.CellTypes.Blank;
import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.CellTypes.TextCell;
import org.lostarmy.utils.ConsoleColors;

import java.io.PrintWriter;
import java.util.List;

import static org.lostarmy.utils.HandlersManager.entityHandler;
import static org.lostarmy.utils.HandlersManager.mapHandler;

public class ScreenHandler {
    public final Cell[][] screenCells;
    public final int mapX;
    public final int mapY;
    public ClientHandler clientHandler;
    public static boolean isServer = false;

    public void setCell(Cell cell, int x, int y) {
        screenCells[x][y] = cell;
    }

    public ScreenHandler(int x, int y) {
        this.mapX = 10;
        this.mapY = 10;
        screenCells = new Cell[x][y];
        for (int i = 0; i < screenCells.length - 1; i++) {
            for (int j = 0; j < screenCells[0].length - 1; j++) {
                screenCells[i][j] = new Blank(i, j);
            }
        }
    }

    public ScreenHandler(int x, int y, boolean isServer, ClientHandler clientHandler) {
        this.mapX = 10;
        this.mapY = 10;
        ScreenHandler.isServer = isServer;
        screenCells = new Cell[x][y];
        for (int i = 0; i < screenCells.length - 1; i++) {
            for (int j = 0; j < screenCells[0].length - 1; j++) {
                screenCells[i][j] = new Blank(i, j);
            }
        }
        this.clientHandler = clientHandler;
    }

    protected void setText(String text, int x, int y) {
        for (int i = 0; i < text.length(); i++) {
            screenCells[x][y + i] = new TextCell(x, y + i, "" + text.charAt(i));
        }
    }

    protected void setText(String text, int x, int y, String color) {
        for (int i = 0; i < text.length(); i++) {
            screenCells[x][y + i] = new TextCell(x, y + i, "" + text.charAt(i), color);
        }
    }

    public void print() {
        if(isServer){
            clearDisplay();
        }

        clearScreen();
        //1 line
        setText("--Stats--", 1, mapY + 2 + 2, ConsoleColors.CYAN);
        setText("Health: " + entityHandler.getPlayer().getMaxHealth() + "/" + entityHandler.getPlayer().getHealth(), 2, mapY + 2 + 2, ConsoleColors.RED);
        setText("Damage: " + entityHandler.getPlayer().getDamage(), 3, mapY + 2 + 2, ConsoleColors.PURPLE);
        setText("Defence: " + entityHandler.getPlayer().getDefence(), 4, mapY + 2 + 2, ConsoleColors.GREEN);
        //2 line
        printInventory(mapY + 2 + 20);
        printControls(mapY + 2 + 50);

        //clearScreen();
        //mapHandler.generateMap();
        mapHandler.update();
        mapHandler.generateLayingItems();
        renderMap();

        int playerX = entityHandler.getPlayer().getX();
        int playerY = entityHandler.getPlayer().getY();

        if (!isServer) {

            for (int i = 0; i < screenCells.length - 1; i++) {
                for (int j = 0; j < screenCells[0].length - 1; j++) {
                    int mapX = playerX - 5 + i;
                    int mapY = playerY - 5 + j;
                    if (screenCells[i][j] instanceof Enemy) {
                        Enemy tempEnemy = entityHandler.getEnemyAt(mapX, mapY);
                        System.out.print(tempEnemy.getEnemyHardness() + screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                    } else {
                        System.out.print(screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                    }
                }
                System.out.print("\n");
            }
        } else {
            for (int i = 0; i < screenCells.length - 1; i++) {
                for (int j = 0; j < screenCells[0].length - 1; j++) {
                    int mapX = playerX - 5 + i;
                    int mapY = playerY - 5 + j;
                    if (screenCells[i][j] instanceof Enemy) {
                        Enemy tempEnemy = entityHandler.getEnemyAt(mapX, mapY);
                        clientHandler.print(tempEnemy.getEnemyHardness() + screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                    } else {
                        clientHandler.print(screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                    }
                }
                clientHandler.println("");
            }
        }
    }

    public static void clearDisplay() {
            for (int i = 0; i < 100; i++) {
                System.out.print("\n");
            }
    }
    public static void clearDisplay(ClientHandler clientHandler) {
        for (int i = 0; i < 100; i++) {
            clientHandler.println("");
        }
    }
    protected void clearScreen() {
        for (int i = 0; i < screenCells.length - 1; i++) {
            for (int j = 0; j < screenCells[0].length - 1; j++) {
                screenCells[i][j] = new Blank(i, j);
            }
        }
    }

    protected void printControls(int y) {
        String color = ConsoleColors.PURPLE;
        setText("--Controls--", 1, y, color);
        setText("WASD - move", 2, y, color);
        setText("F - open inventory", 3, y, color);
    }

    private void printInventory(int y) {
        Player player = entityHandler.getPlayer();
        Inventory inventory = player.getInventory();
        List<Armor> items = player.getInventory().getWornItems();
        setText(inventory.inventoryDisplay, 1, y, ConsoleColors.YELLOW);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == null) {
                continue;
            }
            setText(items.get(i).name, 2 + i, y);
        }
    }

    public void renderMap() {
        int playerX = entityHandler.getPlayer().getX();
        int playerY = entityHandler.getPlayer().getY();

        Cell[][] map = mapHandler.getMap();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int mapX = playerX - 5 + i;
                int mapY = playerY - 5 + j;

                if (mapX >= 0 && mapX < map.length && mapY >= 0 && mapY < map[0].length) {
                    setCell(map[mapX][mapY], i, j);
                } else {
                    setCell(new Blank(i, j), i, j);
                }
            }
        }
    }
}
