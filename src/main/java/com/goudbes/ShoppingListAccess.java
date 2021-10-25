package main.java.com.goudbes;

public class ShoppingListAccess {
    private ShoppingList shoppingList;
    private Person person;
    private boolean canRead;
    private boolean canWrite;

    public ShoppingListAccess(ShoppingList shoppingList, Person person, boolean canRead, boolean canWrite) {
        this.shoppingList = shoppingList;
        this.person = person;
        this.canRead = canRead;
        this.canWrite = canWrite;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean canRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean canWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

}
