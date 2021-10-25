
public class Person {
    private String name;
    private ShoppingList list;

    public Person(String name) {
        this.name = name;
    }

    public void addShoppingList(ShoppingList list) {
        this.list = list;
    }

    public ShoppingList getShoppingList() {
        return list;
    }

    public void deleteShoppingList() {
        list = null;
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
        String lst = (getShoppingList() == null) ? "has no list" : list.getName() + " Size: " + list.size();
        return "Person: " + getName() + " List: " + lst;
    }
}
