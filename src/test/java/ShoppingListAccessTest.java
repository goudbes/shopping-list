import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListAccessTest {
    private final Person person = new Person("Jack");
    private final ShoppingList shoppingLst = new ShoppingList("Shopping", person);

    @Test
    void getShoppingList() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        assertEquals(shoppingLst, a.getShoppingList());
    }

    @Test
    void setShoppingList() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        ShoppingList shplst = new ShoppingList("Coop", person);
        a.setShoppingList(shplst);
        assertEquals(shplst, a.getShoppingList());
    }

    @Test
    void getPerson() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        assertEquals(person, a.getPerson());
    }

    @Test
    void setPerson() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        Person stranger = new Person("Jessica");
        a.setPerson(stranger);
        assertEquals(stranger, a.getPerson());
    }

    @Test
    void canRead() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        assertTrue(a.canRead());
    }

    @Test
    void setCanRead() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        a.setCanRead(false);
        assertFalse(a.canRead());
    }

    @Test
    void canWrite() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        assertTrue(a.canWrite());
    }

    @Test
    void setCanWrite() {
        ShoppingListAccess a = shoppingLst.getAccessForPerson(person);
        a.setCanWrite(false);
        assertFalse(a.canWrite());
    }
}