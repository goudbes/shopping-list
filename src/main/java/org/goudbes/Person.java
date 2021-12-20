package org.goudbes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;

@DatabaseTable(tableName = "persons")
public class Person {
    public static final String NAME_FIELD_NAME = "person_name";
    public static final String EMAIL_FIELD_NAME = "email";

    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = EMAIL_FIELD_NAME, canBeNull = false, unique = true)
    private String email;

    private Set<ShoppingList> lists = new HashSet<>();

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
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
