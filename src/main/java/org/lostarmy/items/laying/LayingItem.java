package org.lostarmy.items.laying;

import org.lostarmy.items.Item;
import org.lostarmy.map.CellTypes.Cell;

public class LayingItem extends Cell {
    public LayingItem(int x, int y, Item item) {
        super(x, y, item.display);
        this.item = item;
    }
    public Item item;
}
