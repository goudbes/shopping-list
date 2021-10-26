import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private final Set<ShoppingList> lists = new HashSet<>();

    @BeforeEach
    public void setUp() {
        person = new Person("Audrey");
        lists.add(new ShoppingList("Coop", person));
    }

    @AfterEach
    public void tearDown() {
        person = null;
        lists.clear();
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
        assertEquals(lists, person.getShoppingLists());
    }

    @Test
    void getShoppingList() {
        assertEquals(lists, person.getShoppingLists());
    }

    @Test
    void deleteShoppingLists() {
        person.deleteShoppingLists();
        assertTrue(person.getShoppingListsNumber() == 0);
    }

    @Test
    void getShoppingListsNumber() {
        assertTrue(person.getShoppingListsNumber() == 1);
    }

    @Test
    void equals() {
        assertTrue(person.equals(person));
        assertFalse(person.equals(null));
        assertFalse(person.equals(new Person("Mike")));
    }

    @Test
    void testToString() {
        assertEquals("Person: Audrey Number of lists: 1", person.toString());
    }

}