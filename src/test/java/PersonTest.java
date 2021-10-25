import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private ShoppingList list;

    @BeforeEach
    public void initialize() {
        person = new Person("Audrey");
        list = new ShoppingList("Coop list", person);
    }

    @AfterEach
    public void tearDown() {
        person = null;
        list = null;
    }

    @Test
    public void testCreatePerson() {
        assertNotNull(person);
    }

    @Test
    public void testGetName() {
        assertSame("Audrey", person.getName());
    }

    @Test
    public void testSetName() {
        person.setName("George");
        assertSame("George", person.getName());
    }


    @Test
    void addShoppingList() {
        assertEquals(list, person.getShoppingList());
    }

    @Test
    void getShoppingList() {
        assertEquals(list, person.getShoppingList());
    }

    @Test
    void deleteShoppingList() {
        person.deleteShoppingList();
        assertNull(person.getShoppingList());
    }

}