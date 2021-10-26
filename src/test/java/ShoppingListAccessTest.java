import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListAccessTest {
    private Person person;
    private Person friend;
    private ShoppingList shoppingLst;
    private ShoppingListAccess a;

    @BeforeEach
    public void setUp() {
        person = new Person("Audrey");
        friend = new Person("Mike");
        shoppingLst = new ShoppingList("Shopping", person);
        a = shoppingLst.getAccessForPerson(person);
    }

    @AfterEach
    public void tearDown() {
        person = null;
        shoppingLst = null;
        a = null;
    }

    @Test
    void getShoppingList() {
        assertEquals(shoppingLst, a.getShoppingList());
    }

    @Test
    void setShoppingList() {
        ShoppingList shplst = new ShoppingList("Coop", person);
        a.setShoppingList(shplst);
        assertEquals(shplst, a.getShoppingList());
    }

    @Test
    void getPerson() {
        assertEquals(person, a.getPerson());
    }

    @Test
    void setPerson() {
        Person stranger = new Person("Jessica");
        a.setPerson(stranger);
        assertEquals(stranger, a.getPerson());
    }

    @Test
    void canRead() {
        assertTrue(a.canRead());
    }

    @Test
    void setCanRead() {
        a.setCanRead(false);
        assertFalse(a.canRead());
    }

    @Test
    void canWrite() {
        assertTrue(a.canWrite());
    }

    @Test
    void setCanWrite() {
        a.setCanWrite(false);
        assertFalse(a.canWrite());
    }

    @Test
    void canDelete() {
        assertTrue(a.canDelete());
    }

    @Test
    void setCanDelete() {
        a.setCanDelete(false);
        assertFalse(a.canDelete());
    }
}