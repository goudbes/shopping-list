package org.goudbes;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private int id;
    private String name;
    private String email;
    private Set<ShoppingList> lists = new HashSet<>();

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
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
        for (ShoppingList l : lists) {
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
        return name.equals(that.name) && email.equals(that.email);
    }

    @Override
    public String toString() {
        return "Person: " + getName() + " Number of lists: " + lists.size();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
