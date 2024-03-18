package org.lostarmy.screen;

import org.lostarmy.ServerWebSocket;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.entities.EntityTypes.Player.Inventory.InventoryItem;
import org.lostarmy.items.Armor;
import org.lostarmy.items.Food;
import org.lostarmy.items.Item;
import org.lostarmy.items.Use;
import org.lostarmy.utils.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lostarmy.utils.HandlersManager.entityHandler;

public class InventoryScreen extends ScreenHandler implements Use {
    private boolean isServer = ScreenHandler.isServer;
    private int selectedLine = 0;

    public InventoryScreen(int x, int y, int mapX, int mapY) {
        super(x, y);
    }

    public void openInventory() {
        boolean isRunning = true;
        while (isRunning) {
            getInventoryDisplay();
            isRunning = readInput();

        }
    }

    private void getInventoryDisplay() {
        clearDisplay();
        clearScreen();
        setText("--Inventory--", 0, 0, ConsoleColors.YELLOW);
        for (int i = 0; i < entityHandler.getPlayer().getInventory().backpack.size(); i++) {
            InventoryItem item = entityHandler.getPlayer().getInventory().backpack.get(i);
            Inventory inventory = entityHandler.getPlayer().getInventory();
            if (item.item instanceof Armor armor) {
                switch (armor.slot) {
                    case HEAD -> {
                        if (inventory.head == armor)
                            setText(item.item.name + " x" + item.amount, i + 1, 1, ConsoleColors.GREEN);
                        else setText(item.item.name + " x" + item.amount, i + 1, 1);
                        continue;
                    }
                    case CHEST -> {
                        if (inventory.chest == armor)
                            setText(item.item.name + " x" + item.amount, i + 1, 1, ConsoleColors.GREEN);
                        else setText(item.item.name + " x" + item.amount, i + 1, 1);
                        continue;
                    }
                    case LEGS -> {
                        if (inventory.legs == armor)
                            setText(item.item.name + " x" + item.amount, i + 1, 1, ConsoleColors.GREEN);
                        else setText(item.item.name + " x" + item.amount, i + 1, 1);
                        continue;
                    }
                    case BOOTS -> {
                        if (inventory.boots == armor)
                            setText(item.item.name + " x" + item.amount, i + 1, 1, ConsoleColors.GREEN);
                        else setText(item.item.name + " x" + item.amount, i + 1, 1);
                        continue;
                    }
                    case WEAPON -> {
                        if (inventory.weapon == armor)
                            setText(item.item.name + " x" + item.amount, i + 1, 1, ConsoleColors.GREEN);
                        else setText(item.item.name + " x" + item.amount, i + 1, 1);
                        continue;
                    }
                }

            }
            setText(item.item.name + " x" + item.amount, i + 1, 1);
        }
        printControls(mapY + 2 + 50);
        print();
    }

    private boolean readInput() {
        int key;
        if (isServer){
            String input;
            try {
                input = ServerWebSocket.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input==null || input.isEmpty()) return true;
            key = input.charAt(0);
            System.out.printf("Key: %s\n", key);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.isEmpty()) return true;
            key = input.charAt(0);
            System.out.printf("Key: %s\n", key);
        }
        switch (key) {
            case 'w', 'W' -> {
                if (selectedLine > 0) {
                    selectedLine--;
                }
            }
            case 's', 'S' -> {
                if (selectedLine < entityHandler.getPlayer().getInventory().backpack.size()) {
                    selectedLine++;
                }
            }
            case 'e', 'E' -> {
                Item item = entityHandler.getPlayer().getInventory().backpack.get(selectedLine).item;
                if (!(item instanceof Armor) && !(item instanceof Food)) {
                    if (isServer) {
                        ServerWebSocket.println("You can't equip/use this item!");
                    } else {
                        System.out.println("You can't equip/use this item!");
                    }
                    return true;
                }
                useItem(item, selectedLine);
                selectedLine = 0;
            }
            case 'q', 'Q' -> {
                if (entityHandler.getPlayer().getInventory().backpack.isEmpty()) {
                    if (isServer) {
                        ServerWebSocket.println("You have nothing to drop!");
                    } else {
                        System.out.println("You have nothing to drop!");
                    }
                    return true;
                }
                InventoryItem item = entityHandler.getPlayer().getInventory().backpack.get(selectedLine);
                entityHandler.getPlayer().getInventory().backpack.remove(item);
                selectedLine = 0;
            }
            case 'f', 'F' -> {
                clearDisplay();
                if (isServer) {
                    ServerWebSocket.println("You closed inventory");
                    return false;
                } else {
                    System.out.println("You closed inventory");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void print() {
        if (!entityHandler.getPlayer().getInventory().backpack.isEmpty()) {
            setText(">", selectedLine + 1, 0, ConsoleColors.YELLOW);
        } else {
            setText("You have nothing in inventory!", 1, 0);
        }
        if (isServer) {
            for (int i = 0; i < screenCells.length - 1; i++) {
                for (int j = 0; j < screenCells[0].length - 1; j++) {
                    ServerWebSocket.print(screenCells[i][j].getDisplay());
                }
                ServerWebSocket.println("");
            }
        } else {
            for (int i = 0; i < screenCells.length - 1; i++) {
                for (int j = 0; j < screenCells[0].length - 1; j++) {
                    System.out.print(screenCells[i][j].getDisplay());
                }
                System.out.print("\n");
            }
        }
    }

    @Override
    protected void printControls(int y) {
        setText("--Controls--", 1, y, ConsoleColors.PURPLE);
        setText("W - previous item", 2, y, ConsoleColors.PURPLE);
        setText("S - next item", 3, y, ConsoleColors.PURPLE);
        setText("E - equip item", 4, y, ConsoleColors.PURPLE);
        setText("Q - drop item", 5, y, ConsoleColors.PURPLE);
        setText("F - close inventory", 6, y, ConsoleColors.PURPLE);
    }
}
