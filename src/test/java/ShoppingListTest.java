
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private final String listName = "Coop";
    private final Person person = new Person("Audrey");

    @Test
    void addShoppingListItem() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        assertTrue(shoppingList.containsItem(shoppingListItem));
    }

    @Test
    void removeShoppingListItem() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        shoppingList.removeShoppingListItem(shoppingListItem);
        assertFalse(shoppingList.containsItem(shoppingListItem));
    }

    @Test
    void clearList() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        shoppingList.clearList();
        assertEquals(0, shoppingList.size());
    }

    @Test
    void size() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv", 1);
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        assertEquals(2, shoppingList.size());
    }

    @Test
    void getNumberOfItemsDone() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv", 1);
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        if (shoppingList.containsItem(shoppingListItemTwo)) {
            shoppingListItemTwo.setDone(true);
            shoppingList.addShoppingListItem(shoppingListItemTwo);
        }
        assertEquals(1, shoppingList.getNumberOfItemsDone());
    }

    @Test
    void getNumberOfItemsNotDone() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListItem shoppingListItem = new ShoppingListItem("Milk", 2);
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv", 1);
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        assertEquals(2, shoppingList.getNumberOfItemsNotDone());
    }

    @Test
    void getName() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        assertEquals("Coop", shoppingList.getName());
    }

    @Test
    void setName() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        shoppingList.setName("Menu");
        assertEquals("Menu", shoppingList.getName());
    }

    @Test
    void getOwner() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        assertEquals(person, shoppingList.getOwner());
    }

    @Test
    void setOwner() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Person personTwo = new Person("Mike");
        shoppingList.setOwner(personTwo);
        assertEquals(personTwo, shoppingList.getOwner());
    }

    @Test
    void getShoppingList() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Set<ShoppingListItem> lst = shoppingList.getShoppingList();
        assertNotNull(lst);
    }

    @Test
    void setShoppingList() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Set<ShoppingListItem> lst = new HashSet<>();
        shoppingList.setShoppingList(lst);
        assertEquals(lst, shoppingList.getShoppingList());
    }


    @Test
    void getAccessForPerson() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        ShoppingListAccess a = shoppingList.getAccessForPerson(person);
        Person fetchedPerson = a.getPerson();
        assertEquals(person,fetchedPerson);
    }

    @Test
    void giveFullAccess() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Person stranger = new Person("Robin");
        shoppingList.giveFullAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        boolean canRead = a.canRead();
        boolean canWrite = a.canWrite();
        assertTrue(canRead);
        assertTrue(canWrite);
    }

    @Test
    void giveReadAccess() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Person stranger = new Person("Robin");
        shoppingList.giveReadAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        boolean canRead = a.canRead();
        boolean canWrite = a.canWrite();
        assertTrue(canRead);
        assertFalse(canWrite);
    }

    @Test
    void removeAccessForPerson() {
        ShoppingList shoppingList = new ShoppingList(listName, person);
        Person stranger = new Person("Robin");
        shoppingList.giveFullAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        assertTrue(shoppingList.removeAccessForPerson(stranger));

    }
}