package org.goudbes;
/**
 * Shopping list item
 */
public class ShoppingListItem {
    private ShoppingList list;
    private String itemName;
    private boolean done;

    public ShoppingListItem(String itemName) {
        this.itemName = itemName;
        this.list = null;
        done = false;
    }

    public ShoppingList getList() {
        return list;
    }

    public void setList(ShoppingList list) {
        this.list = list;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String lst = (list == null) ? "no list" : list.getName();
        return "ShoppingListItem{" +
                "list=" + lst +
                ", itemName='" + itemName + '\'' +
                ", done=" + done +
                '}';
    }
}
