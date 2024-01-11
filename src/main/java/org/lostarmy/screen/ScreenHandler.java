package org.lostarmy.screen;

import org.lostarmy.entities.EntityTypes.Enemy.Enemy;
import org.lostarmy.entities.EntityTypes.Player.Inventory.Inventory;
import org.lostarmy.entities.EntityTypes.Player.Inventory.InventoryItem;
import org.lostarmy.entities.EntityTypes.Player.Inventory.WearableItem;
import org.lostarmy.entities.EntityTypes.Player.Player;
import org.lostarmy.map.CellTypes.Blank;
import org.lostarmy.map.CellTypes.Cell;
import org.lostarmy.map.CellTypes.TextCell;
import org.lostarmy.utils.ConsoleColors;

import java.util.List;

import static org.lostarmy.utils.HandlersManager.entityHandler;
import static org.lostarmy.utils.HandlersManager.mapHandler;
public class ScreenHandler {
    public Cell[][] screenCells;
    public int mapX;
    public int mapY;

    public void setCell(Cell cell, int x, int y){
        screenCells[x][y] = cell;
    }
    public ScreenHandler(int x, int y, int mapX, int mapY){
        this.mapX = mapX;
        this.mapY = mapY;
        screenCells =  new Cell[x][y];
        for (int i = 0; i<screenCells.length-1;i++){
            for(int j = 0;j < screenCells[0].length-1;j++){
                screenCells[i][j] = new Blank(i, j);
            }
        }
    }

    protected void setText(String text, int x, int y){
        for(int i = 0;i<text.length();i++){
            screenCells[x][y+i]=new TextCell(x, y+i, ""+text.charAt(i));
        }
    }
    public void print(){
        clearScreen();
        //1 line
        setText("Health: "+entityHandler.getPlayer().getMaxHealth()+"/"+entityHandler.getPlayer().getHealth(), 1, mapY+2+2);
        setText("Damage: "+entityHandler.getPlayer().getDamage(),2, mapY+2+2);
        setText("Defence: "+entityHandler.getPlayer().getDefence(),3, mapY+2+2);
        //2 line
        printInventory(1, mapY+2+20);
        printControls(1, mapY+2+50);

        //clearScreen();
        mapHandler.generateMap();
        mapHandler.update(entityHandler);
        mapHandler.render();
        for (int i = 0;i<screenCells.length-1;i++){
            for (int j = 0;j<screenCells[0].length-1;j++){
                if (screenCells[i][j] instanceof Enemy){
                    Enemy tempEnemy = entityHandler.getEnemyAt(i, j);
                    System.out.print(tempEnemy.getEnemyHardness()+screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                } else {
                    System.out.print(screenCells[i][j].getDisplay() + ConsoleColors.RESET);
                }
            }
            System.out.print("\n");
        }
    }
    public static void clearDisplay(){
        for (int i = 0;i<100;i++){
            System.out.print("\n");
        }
    }

    protected void clearScreen(){
        for (int i = 0;i<screenCells.length-1;i++){
            for (int j = 0;j<screenCells[0].length-1;j++){
                screenCells[i][j] = new Blank(i, j);
            }
        }
    }
    protected void printControls(int x, int y){
        setText("Controls:", x, y);
        setText("WASD - move", x+1, y);
        setText("F - open inventory", x+2, y);
    }
    private void printInventory(int x, int y){
        Player player = entityHandler.getPlayer();
        Inventory inventory = player.getInventory();
        List<WearableItem> items = player.getInventory().getWornItems();
        setText(inventory.inventoryDisplay, x, y);
        for (int i = 0;i<items.size();i++){
            if (items.get(i) == null){
                continue;
            }
            InventoryItem invItem = items.get(i);
            setText(invItem.item.name, x+i+1, y);
        }
    }
}
