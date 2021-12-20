package org.goudbes;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CLI {

    private final static String DATABASE_URL = "jdbc:sqlite:cliShopping.db";

    private Dao<Person, Integer> personDao;
    private Dao<ShoppingList, Integer> shoppinglistDao;

    private void setupDB() throws ClassNotFoundException, SQLException {
        JdbcConnectionSource connectionSource = null;
        Class.forName("org.sqlite.JDBC");
        connectionSource = new JdbcConnectionSource(DATABASE_URL);

        personDao = DaoManager.createDao(connectionSource, Person.class);
        shoppinglistDao = DaoManager.createDao(connectionSource, ShoppingList.class);

        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, ShoppingList.class);
    }

    private Panel addPerson() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Name"));
        final TextBox txtName = new TextBox().addTo(panel);

        panel.addComponent(new Label("E-mail"));
        final TextBox txtMail = new TextBox().addTo(panel);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        new Button("Add person", new Runnable() {
            @Override
            public void run() {
                Person person = new Person(txtName.getText(), txtMail.getText());
                try {
                    personDao.createIfNotExists(person);
                } catch (SQLException e) {
                    // FIXME: Handle this
                    e.printStackTrace();
                }
                // FIXME: close panel when done
            }
        }).addTo(panel);
        return panel;
    }

    void start() throws IOException, SQLException, ClassNotFoundException {
        setupDB();

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();
        WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        MenuBar menubar = new MenuBar();

        // "File" menu
        Menu menuFile = new Menu("Menu");
        menubar.add(menuFile);
        menuFile.add(new MenuItem("Create person", new Runnable() {
            @Override
            public void run() {
                Panel panel = addPerson();
                window.setComponent(panel);
            }
        })).add(new MenuItem("Exit", new Runnable() {
            public void run() {
                System.exit(0);
            }
        }));

        // "Help" menu
        Menu menuHelp = new Menu("Help");
        menubar.add(menuHelp);
        menuHelp.add(new MenuItem("Homepage", new Runnable() {
            public void run() {
                MessageDialog.showMessageDialog(
                        textGUI, "Homepage", "https://github.com/mabe02/lanterna", MessageDialogButton.OK);
            }
        }));
        menuHelp.add(new MenuItem("About", new Runnable() {
            public void run() {
                MessageDialog.showMessageDialog(
                        textGUI, "About", "Lanterna drop-down menu", MessageDialogButton.OK);
            }
        }));


        window.setComponent(menubar);

        textGUI.addWindowAndWait(window);
    }

}
