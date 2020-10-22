import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookView extends JFrame implements ActionListener {

    private JFrame frame, editFrame, buddyFrame, removeFrame;
    private JPanel panel;
    private JMenuBar menuBar;

    public AddressBookView(String name) {
        super(name);
        initalizeFrame(name);
    }

    private void initalizeFrame(String name) {
        frame = new JFrame(name);
        panel = new JPanel();

        frame.add(panel);
        frame.setSize(200, 400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        makeMenu();

        frame.setJMenuBar(menuBar);
    }

    private void makeMenu() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(text.FILE.name());
        menuBar.add(fileMenu);

        /*JMenu editMenu = new JMenu(text.EDIT.name());
        menuBar.add(editMenu);
        editMenu.setEnabled(false);*/

        JMenu buddyMenu = new JMenu(text.BUDDYINFO.name());
        menuBar.add(buddyMenu);
        buddyMenu.setEnabled(false);

        JMenuItem item;

        item = new JMenuItem(text.NEW.name());
        item.addActionListener(this);
        fileMenu.add(item);

        item = new JMenuItem(text.SAVE.name());
        item.addActionListener(this);
        fileMenu.add(item);
        item.setEnabled(false);                     // disable this item at initialization

        item = new JMenuItem(text.DISPLAY.name());
        item.addActionListener(this);
        fileMenu.add(item);
        item.setEnabled(false);                     // disable this item at initialization

        /*item = new JMenuItem(text.EDITBUDDY.name());
        item.addActionListener(this);
        editMenu.add(item);
        item.setEnabled(false); */                    // disable this item at initialization

        item = new JMenuItem(text.REMOVE.name());
        item.addActionListener(this);
        buddyMenu.add(item);
        item.setEnabled(false);                     // disable this item at initialization

        item = new JMenuItem(text.ADD.name());
        item.addActionListener(this);
        buddyMenu.add(item);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        /*BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);*/
        AddressBookView example = new AddressBookView("GUI Address Book");
    }
}
