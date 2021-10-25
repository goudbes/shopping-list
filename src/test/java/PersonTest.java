import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testCreatePerson() {
        Person person = new Person("Audrey");
        assertNotNull(person);
    }

    @Test
    public void testGetName() {
        Person person = new Person("Audrey");
        assertSame("Audrey", person.getName());
    }

    @Test
    public void testSetName() {
        Person person = new Person("Mike");
        person.setName("George");
        assertSame("George", person.getName());
    }


    @Test
    void addShoppingList() {
        Person person = new Person("Audrey");
        ShoppingList list = new ShoppingList("Coop list", person);
        assertEquals(list, person.getShoppingList());
    }

    @Test
    void getShoppingList() {
        Person person = new Person("Audrey");
        ShoppingList list = new ShoppingList("Coop list", person);
        assertEquals(list, person.getShoppingList());
    }

    @Test
    void deleteShoppingList() {
        Person person = new Person("Audrey");
        ShoppingList list = new ShoppingList("Coop list", person);
        person.deleteShoppingList();
        assertNull(person.getShoppingList());
    }
}