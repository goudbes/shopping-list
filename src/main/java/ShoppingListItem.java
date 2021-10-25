
/**
 * Shopping list item
 */
public class ShoppingListItem {
    private ShoppingList list;
    private String itemName;
    private int quantity;
    private boolean done;

    public ShoppingListItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                ", quantity=" + quantity +
                ", done=" + done +
                '}';
    }
}
