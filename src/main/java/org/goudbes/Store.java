package org.goudbes;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private final Map<String,Person> persons = new HashMap<>();

    private static Store instance = new Store();
    public static Store getInstance(){
        return instance;
    }

    private Store(){
        persons.put("Ada", new Person("Ada", "ada@mail.com"));
        persons.put("Mike", new Person("Mike","mike@mail.com"));
        persons.put("Jake", new Person("Jake","jake@mail.com"));
    }

    public Person getPerson(String name) {
        return persons.get(name);
    }

    public void addPerson(Person person) {
        persons.put(person.getName(), person);
    }
}
