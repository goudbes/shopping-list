package org.goudbes;

import javax.xml.transform.Result;
import java.sql.*;

public class Actions {

    private static Connection connect() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:D:\\Code\\shopping_list.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static boolean personExists(String email) {
        boolean exists = false;
        String sql = "Select 1 from persons where email = ?";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            exists = rs.next();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public static void addPerson(Person person) {
        if (personExists(person.getEmail())) {
            System.out.println(person.getName() + " already exists and could not be added.");
            return;
        }
        String sql = "INSERT INTO persons(person_name,email) VALUES(?,?)";
        try (Connection connection = connect();
             PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, person.getName());
            prepareStatement.setString(2, person.getEmail());
            prepareStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getPersonId(Person person) {
        if (person.getId() != 0) return person.getId();
        int id = person.getId();
        String sql = "SELECT person_id FROM persons WHERE email = ?";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("person_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static void deletePerson(String name, String email) {
        Person person = new Person(name, email);
        person.setId(getPersonId(person));
        deleteShoppingListsPerPerson(person);
        String sql = "DELETE FROM persons WHERE person_name = ? and email = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteShoppingListsPerPerson(Person person) {
        String sql = "DELETE FROM shoppinglist WHERE owner_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printPersons() {
        String sql = "SELECT person_id, person_name, email FROM persons";
        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("person_id") + "\t" +
                        resultSet.getString("person_name") + "\t" +
                        resultSet.getString("email"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean shoppingListExists(String name, Person owner) {
        boolean exists = false;
        String sql = "Select 1 from shoppinglist where sl_name = ? and owner_id = ?";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, owner.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public static void addShoppingList(ShoppingList shoppingList) {
        Person owner = shoppingList.getOwner();
        owner.setId(getPersonId(owner));
        String shoppingListName = shoppingList.getName();
        if (shoppingListExists(shoppingListName, owner)) {
            System.out.println(shoppingListName + " already exists and could not be added.");
            return;
        }
        String sql = "INSERT INTO shoppinglist(sl_name,owner_id) VALUES(?,?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, shoppingList.getName());
            preparedStatement.setInt(2, owner.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteShoppingList(String name, int ownerid) {
        String sql = "DELETE FROM shoppinglist WHERE sl_name = ? and owner_id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, ownerid);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printShoppingLists() {
        String sql = "SELECT sl_id, sl_name, owner_id FROM shoppinglist";
        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("sl_id") + "\t" +
                        rs.getString("sl_name") + "\t" +
                        rs.getInt("owner_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printShoppingListsForPerson(int ownerid) {
        String sql = "SELECT sl_id, name FROM shoppinglist WHERE ownerid =" + ownerid;
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("sl_id") + "\t" +
                        rs.getString("sl_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
