package org.goudbes;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBMain {
    // we are using the in-memory H2 database
    private final static String DATABASE_URL = "jdbc:sqlite:memory:mydb";

    private Dao<Person, Integer> personDao;
    private Dao<ShoppingList, Integer> shoppinglistDao;

    public static void main(String[] args) throws Exception {
        // turn our static method into an instance of Main
        new DBMain().doMain(args);
    }

    private void doMain(String[] args) throws Exception {
        JdbcConnectionSource connectionSource = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            setupDatabase(connectionSource);
            readWriteData();
            System.out.println("Success.");
        } finally {
            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }

    private void setupDatabase(ConnectionSource connectionSource) throws Exception {

        personDao = DaoManager.createDao(connectionSource, Person.class);
        shoppinglistDao = DaoManager.createDao(connectionSource, ShoppingList.class);

        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, ShoppingList.class);
    }

    private void readWriteData() throws Exception {
        Person person = new Person("Andrine", "andrine@gmail.com");
        personDao.create(person);
        ShoppingList shoppingList = new ShoppingList("Weekend", person);
        shoppinglistDao.create(shoppingList);
        ShoppingList shoppingList_ = new ShoppingList("Monday", person);
        shoppinglistDao.create(shoppingList_);

        QueryBuilder<ShoppingList, Integer> statementBuilder = shoppinglistDao.queryBuilder();
        statementBuilder.where().eq(ShoppingList.OWNER_ID_FIELD_NAME, person);
        List<ShoppingList> lists = shoppinglistDao.query(statementBuilder.prepare());

        for (ShoppingList sl :
                lists) {
            personDao.refresh(sl.getOwner());
            System.out.println("Shopping list name: " + sl.getName() + " Owner: " + sl.getOwner().getName());
        }

        assertEquals(2, lists.size());
        assertTrue(shoppinglistDao.objectsEqual(shoppingList, lists.get(0)));
        assertTrue(shoppinglistDao.objectsEqual(shoppingList_, lists.get(1)));

        assertEquals(person.getId(), lists.get(0).getOwner().getId());
        assertEquals(person.getId(), lists.get(1).getOwner().getId());

        assertEquals(1, personDao.refresh(lists.get(0).getOwner()));
        assertEquals(1, personDao.refresh(lists.get(1).getOwner()));

        assertEquals(person.getName(), lists.get(0).getOwner().getName());
        assertEquals(person.getName(), lists.get(1).getOwner().getName());

    }
}
