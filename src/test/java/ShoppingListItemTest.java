import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListItemTest {

    private Person person;
    private ShoppingList list;
    private ShoppingListItem item;

    @BeforeEach
    public void initialize() {
        person = new Person("Mike");
        list = new ShoppingList("Coop", person);
        item = new ShoppingListItem("Falukorv", 2);
    }

    @AfterEach
    public void tearDown() {
        person = null;
        list = null;
        item = null;
    }

    @Test
    void getList() {
        list.addShoppingListItem(item);
        assertEquals(list, item.getList());
    }

    @Test
    void setList() {
        list.addShoppingListItem(item);
        ShoppingList newList = new ShoppingList("Meny", person);
        item.setList(newList);
        assertEquals(newList, item.getList());
    }

    @Test
    void getItemName() {
        list.addShoppingListItem(item);
        assertEquals("Falukorv", item.getItemName());
    }

    @Test
    void setItemName() {
        list.addShoppingListItem(item);
        item.setItemName("Milk");
        assertEquals("Milk", item.getItemName());
    }

    @Test
    void getQuantity() {
        list.addShoppingListItem(item);
        assertEquals(2, item.getQuantity());
    }

    @Test
    void setQuantity() {
        list.addShoppingListItem(item);
        item.setQuantity(23);
        assertEquals(23, item.getQuantity());
    }

    @Test
    void isDone() {
        list.addShoppingListItem(item);
        assertFalse(item.isDone());
    }

    @Test
    void setDone() {
        list.addShoppingListItem(item);
        item.setDone(true);
        assertTrue(item.isDone());
    }

    @Test
    void testToString() {
        list.addShoppingListItem(item);
        assertEquals("ShoppingListItem{list=" + list.getName() + ", itemName='" +
                item.getItemName() + "', quantity=" + item.getQuantity() + ", done=false}", item.toString());
    }
}