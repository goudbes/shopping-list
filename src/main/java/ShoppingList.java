
import java.util.HashSet;
import java.util.Set;

/**
 * Shopping list containing shopping items
 */
public class ShoppingList {
    private String name;
    private Person owner;
    private Set<ShoppingListItem> shoppingList = new HashSet<>();
    private Set<ShoppingListAccess> accessList = new HashSet<>();

    public ShoppingList(String name, Person owner) {
        this.name = name;
        this.owner = owner;
        owner.addShoppingList(this);
        giveFullAccess(owner);
    }

    public ShoppingListAccess getAccessForPerson(Person person) {
        for (ShoppingListAccess a : accessList) {
            if (a.getPerson().equals(person)) return a;
        }
        return null;
    }

    public boolean removeAccessForPerson(Person person) {
        if (owner.equals(person)) {
            System.out.println("Cannot remove access for the owner");
            return false;
        }
        ShoppingListAccess a = getAccessForPerson(person);
        if (a != null) {
            return accessList.remove(a);
        } else return false;
    }

    public void giveFullAccess(Person person) {
        ShoppingListAccess access = new ShoppingListAccess(this, person, true, true);
        accessList.add(access);
    }

    public void giveReadAccess(Person person) {
        ShoppingListAccess access = new ShoppingListAccess(this, person, true, false);
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
}
