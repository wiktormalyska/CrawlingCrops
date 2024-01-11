package org.lostarmy.screen;

import org.lostarmy.entities.EntityTypes.Player.Inventory.InventoryItem;
import org.lostarmy.utils.ConsoleColors;
import org.lostarmy.utils.HandlersManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lostarmy.utils.HandlersManager.entityHandler;

public class InventoryScreen extends ScreenHandler{
    private int selectedLine=0;
    public InventoryScreen(int x, int y, int mapX, int mapY) {
        super(x, y, mapX, mapY);
    }

    public void openInventory(){
        boolean isRunning = true;
        while (isRunning){
            getInventoryDisplay();
            isRunning = readInput();
        }
    }
    private void getInventoryDisplay(){
        clearScreen();
        setText("---Inventory---", 0, 0);
        for (int i = 0; i< entityHandler.getPlayer().getInventory().backpack.size(); i++){
            InventoryItem item = entityHandler.getPlayer().getInventory().backpack.get(i);
            setText(item.item.name+" x"+item.amount, i+1, 1);
        }
        printControls(1, mapY+2+50);
        print();
    }
    private boolean readInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (input.length() == 0) return true;
        int key = input.charAt(0);
        switch (key){
            case 'w','W' -> {
                if (selectedLine>0){
                    selectedLine--;
                }
            }
            case 's','S' -> {
                if (selectedLine< entityHandler.getPlayer().getInventory().backpack.size()){
                    selectedLine++;
                }
            }
            case 'e','E' -> {
                InventoryItem item = entityHandler.getPlayer().getInventory().backpack.get(selectedLine);
                if (!item.item.isWearable){
                    System.out.println("You can't equip this item!");
                    return true;
                }

                entityHandler.getPlayer().getInventory().wearItem(item, entityHandler.getPlayer());
            }
            case 'q','Q' -> {
                InventoryItem item = entityHandler.getPlayer().getInventory().backpack.get(selectedLine);
                entityHandler.getPlayer().getInventory().backpack.remove(item);
            }
            case 'f','F' -> {
                System.out.println("You closed inventory");
                return false;
            }
        }
        return true;
    }
    @Override
    public void print(){
        if (!entityHandler.getPlayer().getInventory().backpack.isEmpty()){
            setText(">", selectedLine+1, 0);
        } else {
            setText("You have nothing in inventory!", 1, 0);
        }
        for (int i=0 ;i<screenCells.length-1; i++){
            for (int j = 0; j<screenCells[0].length-1; j++){
                System.out.print(screenCells[i][j].getDisplay() + ConsoleColors.RESET);
            }
            System.out.print("\n");
        }
    }

    @Override
    protected void printControls(int x, int y){
        setText("Controls:", x, y);
        setText("W - previous item", x+1, y);
        setText("S - next item", x+2, y);
        setText("E - equip item", x+3, y);
        setText("Q - drop item", x+4, y);
        setText("F - close inventory", x+5, y);
    }
}
