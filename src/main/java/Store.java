import java.util.HashMap;
import java.util.Map;

public class Store {
    private final Map<String,Person> persons = new HashMap<>();

    private static Store instance = new Store();
    public static Store getInstance(){
        return instance;
    }

    private Store(){
        persons.put("Ada", new Person("Ada"));
        persons.put("Mike", new Person("Mike"));
        persons.put("Jake", new Person("Jake"));
    }

    public Person getPerson(String name) {
        return persons.get(name);
    }

    public void addPerson(Person person) {
        persons.put(person.getName(), person);
    }
}
