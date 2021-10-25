public class Main {

    public static void main(String[] args) {
        Person person = new Person("Audrey");
        ShoppingList list = new ShoppingList("Coop", person);
        System.out.println(person);
        list.addShoppingListItem(new ShoppingListItem("Milk", 2));
        list.addShoppingListItem(new ShoppingListItem("Bread", 1));
        list.printShoppingList();
        Person stranger = new Person("Mike");
        System.out.println(stranger);
        list.giveReadAccess(stranger);
        list.removeAccessForPerson(person);
        list.printAccessList();
    }
}
