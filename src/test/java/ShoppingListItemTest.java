import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListItemTest {

    @Test
    void getList() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        assertEquals(list, item.getList());
    }

    @Test
    void setList() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        ShoppingList newList = new ShoppingList("Meny", person);
        item.setList(newList);
        assertEquals(newList, item.getList());
    }

    @Test
    void getItemName() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        assertEquals("Falukorv", item.getItemName());
    }

    @Test
    void setItemName() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        item.setItemName("Milk");
        assertEquals("Milk", item.getItemName());
    }

    @Test
    void getQuantity() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 3);
        list.addShoppingListItem(item);
        assertEquals(3, item.getQuantity());
    }

    @Test
    void setQuantity() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        item.setQuantity(23);
        assertEquals(23, item.getQuantity());
    }

    @Test
    void isDone() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        assertFalse(item.isDone());
    }

    @Test
    void setDone() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 2);
        list.addShoppingListItem(item);
        item.setDone(true);
        assertTrue(item.isDone());
    }

    @Test
    void testToString() {
        Person person = new Person("Mike");
        ShoppingList list = new ShoppingList("Coop", person);
        ShoppingListItem item = new ShoppingListItem("Falukorv", 3);
        list.addShoppingListItem(item);
        assertEquals("ShoppingListItem{list=" + list.getName() + ", itemName='" +
                item.getItemName() + "', quantity=" + item.getQuantity() + ", done=false}", item.toString());
    }
}