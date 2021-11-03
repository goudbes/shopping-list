package org.goudbes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {
    private ShoppingList shoppingList;
    private ShoppingListItem shoppingListItem;
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Audrey","audrey@mail.com");
        shoppingList = new ShoppingList("Coop", person);
        shoppingListItem = new ShoppingListItem("Milk");
    }

    @AfterEach
    public void tearDown() {
        person = null;
        shoppingList = null;
        shoppingListItem = null;
    }

    @Test
    void addShoppingListItem() {
        shoppingList.addShoppingListItem(shoppingListItem);
        assertTrue(shoppingList.containsItem(shoppingListItem));
    }

    @Test
    void removeShoppingListItem() {
        shoppingList.addShoppingListItem(shoppingListItem);
        shoppingList.removeShoppingListItem(shoppingListItem);
        assertFalse(shoppingList.containsItem(shoppingListItem));
    }

    @Test
    void clearList() {
        shoppingList.addShoppingListItem(shoppingListItem);
        shoppingList.clearList();
        assertEquals(0, shoppingList.size());
    }

    @Test
    void size() {
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv");
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        assertEquals(2, shoppingList.size());
    }

    @Test
    void getNumberOfItemsDone() {
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv");
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        shoppingListItemTwo.setDone(true);
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        assertEquals(1, shoppingList.getNumberOfItemsDone());
    }

    @Test
    void getNumberOfItemsNotDone() {
        shoppingList.addShoppingListItem(shoppingListItem);
        ShoppingListItem shoppingListItemTwo = new ShoppingListItem("Falukorv");
        shoppingList.addShoppingListItem(shoppingListItemTwo);
        assertEquals(2, shoppingList.getNumberOfItemsNotDone());
    }

    @Test
    void getName() {
        assertEquals("Coop", shoppingList.getName());
    }

    @Test
    void setName() {
        shoppingList.setName("Menu");
        assertEquals("Menu", shoppingList.getName());
    }

    @Test
    void getOwner() {
        assertEquals(person, shoppingList.getOwner());
    }

    @Test
    void setOwner() {
        Person personTwo = new Person("Mike","mike@mail.com");
        shoppingList.setOwner(personTwo);
        assertEquals(personTwo, shoppingList.getOwner());
    }

    @Test
    void getShoppingList() {
        Set<ShoppingListItem> lst = shoppingList.getShoppingList();
        assertNotNull(lst);
    }

    @Test
    void setShoppingList() {
        Set<ShoppingListItem> lst = new HashSet<>();
        shoppingList.setShoppingList(lst);
        assertEquals(lst, shoppingList.getShoppingList());
    }


    @Test
    void getAccessForPerson() {
        Person stranger = new Person("Mike", "mike@mail.com");
        shoppingList.giveFullAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        assertEquals(stranger, a.getPerson());
    }

    @Test
    void giveFullAccess() {
        Person stranger = new Person("Robin","robin@mail.com");
        shoppingList.giveFullAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        assertTrue(a.canRead());
        assertTrue(a.canWrite());
    }

    @Test
    void giveReadAccess() {
        Person stranger = new Person("Robin","robin@mail.com");
        shoppingList.giveReadAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        assertTrue(a.canRead());
        assertFalse(a.canWrite());
    }

    @Test
    void giveAdminAccess() {
        ShoppingListAccess a = shoppingList.getAccessForPerson(person);
        assertTrue(a.canRead());
        assertTrue(a.canWrite());
        assertTrue(a.canDelete());
    }

    @Test
    void removeAccessForPerson() {
        Person stranger = new Person("Robin","robin@mail.com");
        Person friend = new Person("Jake","jake@mail.com");
        shoppingList.giveFullAccess(stranger);
        ShoppingListAccess a = shoppingList.getAccessForPerson(stranger);
        assertTrue(shoppingList.removeAccessForPerson(stranger));
        assertFalse(shoppingList.removeAccessForPerson(friend));
    }

    @Test
    void deleteShoppingList() {
        assertTrue(person.deleteShoppingList("Coop"));
    }
}