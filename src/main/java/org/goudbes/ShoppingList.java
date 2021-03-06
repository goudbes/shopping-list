package org.goudbes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Shopping list containing shopping items
 */
@DatabaseTable(tableName = "shopping_lists")
public class ShoppingList {
    public static final String SHOPPINGLIST_NAME_FIELD_NAME = "sl_name";
    public static final String OWNER_ID_FIELD_NAME = "owner_id";

    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField(columnName = SHOPPINGLIST_NAME_FIELD_NAME, uniqueCombo = true, canBeNull = false)
    private String name;

    @DatabaseField(foreign = true, columnName = OWNER_ID_FIELD_NAME, uniqueCombo = true, canBeNull = false)
    private Person owner;

    private Set<ShoppingListItem> shoppingList = new HashSet<>();
    private Set<ShoppingListAccess> accessList = new HashSet<>();

    public ShoppingList() {
    }

    public ShoppingList(String name, Person owner) {
        this.name = name;
        this.owner = owner;
        owner.addShoppingList(this);
        giveAdminAccess(owner);
    }

    public ShoppingListAccess getAccessForPerson(Person person) {
        for (ShoppingListAccess a : accessList) {
            if (a.getPerson().equals(person)) return a;
        }
        return null;
    }

    public boolean removeAccessForPerson(Person person) {
        ShoppingListAccess a = getAccessForPerson(person);
        if (a != null) {
            return accessList.remove(a);
        } else return false;
    }

    public void giveFullAccess(Person person) {
        ShoppingListAccess access = new ShoppingListAccess(this, person,
                true, true, false);
        accessList.add(access);
    }

    public void giveReadAccess(Person person) {
        ShoppingListAccess access = new ShoppingListAccess(this, person,
                true, false, false);
        accessList.add(access);
    }

    public void giveAdminAccess(Person person) {
        ShoppingListAccess access = new ShoppingListAccess(this, person,
                true, true, true);
        accessList.add(access);
    }

    public void addShoppingListItem(ShoppingListItem item) {
        item.setList(this);
        shoppingList.add(item);
    }

    public boolean containsItem(ShoppingListItem item) {
        return shoppingList.contains(item);
    }

    public void removeShoppingListItem(ShoppingListItem item) {
        boolean removed = shoppingList.remove(item);
        if (!removed) System.out.println(item.getItemName() + " was not found in the list");
    }

    public void clearList() {
        shoppingList.clear();
    }

    public int size() {
        return shoppingList.size();
    }

    public int getNumberOfItemsDone() {
        int done = 0;
        for (ShoppingListItem i : shoppingList) {
            if (i.isDone()) done++;
        }
        return done;
    }

    public void printShoppingList() {
        System.out.println("Shopping list: " + getName() + " Contains: " + size() + " items");
        for (ShoppingListItem i : shoppingList) {
            System.out.println(i.getItemName());
        }
    }

    public void printAccessList() {
        System.out.println("Access list for shopping list " + getName());
        for (ShoppingListAccess a : accessList) {
            Person p = a.getPerson();
            System.out.println("Person: " + p.getName() + " Read: " + a.canRead() +
                    " Write: " + a.canWrite());
        }
    }

    public int getNumberOfItemsNotDone() {
        return size() - getNumberOfItemsDone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Set<ShoppingListItem> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<ShoppingListItem> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
