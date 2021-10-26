public class Main {

    public static void main(String[] args) {
        Person person = new Person("Audrey");
        ShoppingList list = new ShoppingList("Coop", person);
        System.out.println(person.getShoppingListsNumber());
        System.out.println(person.deleteShoppingList("Coop"));
        System.out.println("After deletion: " + person.getShoppingListsNumber());
        list.addShoppingListItem(new ShoppingListItem("Milk"));
        list.addShoppingListItem(new ShoppingListItem("Bread"));
        list.printShoppingList();
        Person stranger = new Person("Mike");
        System.out.println(stranger);
        list.giveReadAccess(stranger);
        list.removeAccessForPerson(person);
        list.printAccessList();
    }
}
