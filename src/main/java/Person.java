import java.util.HashSet;
import java.util.Set;

public class Person {
    private String name;
    private Set<ShoppingList> lists = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void addShoppingList(ShoppingList list) {
        lists.add(list);
    }
    public Set<ShoppingList> getShoppingLists() {
        return lists;
    }

    public void deleteShoppingLists() {
        lists.clear();
    }

    public int getShoppingListsNumber() {
        return lists.size();
    }

    public boolean deleteShoppingList(String shoppingListName) {
        ShoppingList lst = null;
        for (ShoppingList l: lists) {
            if (l.getName().equals(shoppingListName)) {
                lst = l;
            }
        }
        return lists.remove(lst);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return name.equals(that.name);
    }

    @Override
    public String toString() {
        return "Person: " + getName() + " Number of lists: " + lists.size();
    }
}
