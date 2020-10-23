import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddressBookView extends JFrame implements ActionListener, ListSelectionListener {

    private JFrame frame, editFrame, buddyFrame, removeFrame;
    private JPanel panel;
    private JMenuBar menuBar;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Listselectionevent occured");
    }

    private enum text {NEW, SAVE, ADD, DISPLAY, NAME,
        ADDRESS, PHONENUM, FILE, BUDDYINFO,
        EDIT, EDITBUDDY, REMOVE};
    private AddressBook addressBook;
    private JTextArea name, address, number;

    private JList<BuddyInfo> list;

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
        frame.setVisible(true);
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
        if (e.getSource() instanceof JMenuItem)
        {
            // create new address book and enables menus and menu items
            if (e.getActionCommand().equals(text.NEW.name()))
            {
                addressBook = new AddressBook();              // initialize the address book
                System.out.println("Created");
                // enable the disable menu tab and menu item
                menuBar.getMenu(1).setEnabled(true);
                if (menuBar.getMenu(0).getMenuComponent(2).isEnabled())
                    menuBar.getMenu(0).getMenuComponent(2).setEnabled(false);
                if (menuBar.getMenu(1).getMenuComponent(0).isEnabled()) {
                    menuBar.getMenu(1).getMenuComponent(0).setEnabled(false);
                }
                //menuBar.getMenu(2).setEnabled(true);
                //menuBar.getMenu(0).getMenuComponent(1).setEnabled(true);
                display();

            }


            // save address book to file
            /*else if (e.getActionCommand().equals(text.SAVE.name()))
            {
                System.out.println("Saved");

                BuddyInfo [] ab_array = addressBook.getAddressBookArray();    // get an array of buddyInfos

                writeTOfile(ab_array);                               // call writeTOfile method
            }*/


            // add a buddy to the address book
            else if (e.getActionCommand().equals(text.ADD.name()))
            {
                System.out.println("Added");

                if (! menuBar.getMenu(0).getMenuComponent(2).isEnabled())
                    menuBar.getMenu(0).getMenuComponent(2).setEnabled(true);

                if (! menuBar.getMenu(1).getMenuComponent(0).isEnabled())
                    menuBar.getMenu(1).getMenuComponent(0).setEnabled(true);

                if (! menuBar.getMenu(1).getMenuComponent(1).isEnabled())
                    menuBar.getMenu(1).getMenuComponent(1).setEnabled(true);

                makeBuddyFrame();

            }

            // display address book to the GUI
            else if (e.getActionCommand().equals(text.DISPLAY.name()))
            {
                System.out.println("Displayed");

                display();

            }

            // editing a buddy's information
            /*else if (e.getActionCommand().equals(text.EDITBUDDY.name()))
            {
                System.out.println("Edited");
            }*/


            // removing a buddy from the address book
            else if (e.getActionCommand().equals(text.REMOVE.name()))
            {
                if(list.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select a buddy you would like to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    System.out.println("Removed");
                    int response = JOptionPane.YES_NO_OPTION;
                    JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove " + list.getSelectedValue().getName() + " from the address book?", "Warning", response);

                    if (response == JOptionPane.YES_OPTION) {
                        addressBook.removeBuddy(list.getSelectedValue());
                    }
                }

                //removeFrame();



            }
        }

        if (e.getSource() instanceof JButton)
        {
            if (e.getActionCommand().equals(text.ADD.name()))
            {
                System.out.println("Button Pressed");

                BuddyInfo bI = new BuddyInfo(name.getText(), address.getText(), number.getText());
                addressBook.addBuddy(bI);

                buddyFrame.dispose();
            }
            else if (e.getActionCommand().equals(text.REMOVE.name()))
            {
                System.out.println("Button Pressed");

                BuddyInfo bI = new BuddyInfo(name.getText(), address.getText(), number.getText());
                addressBook.removeBuddy(bI);

                removeFrame.dispose();



            }
        }

    }

    private void display() {
        // JLabel
        JLabel label;

        // GridLayout with row size = number of contacts and col size = 1
        GridLayout gl = new GridLayout(addressBook.getBuddyInfos().size(), 1);

        // remove previous JPanel
        frame.remove(panel);

        // create new JPanel
        panel =  new JPanel(gl);

        // add JPanel to JFrame
        frame.add(panel);

        //DefaultListModel<BuddyInfo>listModel = addressBook.getBuddyInfos();
        list = new JList(addressBook.getBuddyInfos().toArray());
        list.addListSelectionListener(this);
        ScrollPane pane = new ScrollPane();
        pane.add(list);
        panel.add(pane);
        /*ArrayList<BuddyInfo> ab_array = addressBook.getBuddyInfos();


        // for each BuddyInfo in the array, display on GUI
        for (BuddyInfo bI: ab_array)
        {
            String nameString = text.NAME.name() + ": " + bI.getName() + " "
                    + text.ADDRESS.name()+ ": " + bI.getAddress() + " "
                    + text.PHONENUM.name() + ": " + bI.getNumber();

            label = new JLabel(nameString);

            panel.add(label);

        }*/

        frame.setVisible(true);
    }

    private void makeBuddyFrame()
    {
        //  initializing JTextArea instances
        name = new JTextArea();
        address = new JTextArea();
        number = new JTextArea();

        // create a new JFrame
        buddyFrame = new JFrame("Add BuddyInfo");

        // set the size and closing features
        buddyFrame.setSize(200, 400);
        buddyFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // create a JButton
        JButton button = new JButton(text.ADD.name());

        // add an actionListener to the button
        button.addActionListener(this);

        // create a new JPanel with BorderLayout
        JPanel buddyPanel = new JPanel(new BorderLayout());

        // create a GridLayout
        GridLayout gl = new GridLayout(3, 2);

        // set the GridLayout vertical gaps
        gl.setVgap(2);

        // create a new JPanel with GridLayout
        JPanel buddyInfoPanel = new JPanel(gl);


        // add the JLabels and JTextArea instances to the buddyInfoPanel
        buddyInfoPanel.add(new JLabel(text.NAME.name()));
        buddyInfoPanel.add(this.name);
        buddyInfoPanel.add(new JLabel(text.ADDRESS.name()));
        buddyInfoPanel.add(this.address);
        buddyInfoPanel.add(new JLabel(text.PHONENUM.name()));
        buddyInfoPanel.add(this.number);


        // add the button to the buddyPanel at the bottom
        buddyPanel.add(button, BorderLayout.SOUTH);

        // add the buddyInfoPanel to the buddyPanel at the center
        buddyPanel.add(buddyInfoPanel, BorderLayout.CENTER);

        // add the buddyPanel to the buddyFrame
        buddyFrame.add(buddyPanel);

        // set the location of the buddyFrame to be at FRAMEDIMENSION[0], FRAMEDIMENSION[1]
        buddyFrame.setLocation(200, 400);

        // set buddyFrame visible
        buddyFrame.setVisible(true);

    }

    private void removeFrame() {
        removeFrame = new JFrame("Removing a buddy");

        name = new JTextArea();
        address = new JTextArea();
        number = new JTextArea();

        // create a new JFrame

        // set the size and closing features
        removeFrame.setSize(200, 400);
        removeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // create a JButton
        JButton button = new JButton(text.REMOVE.name());

        // add an actionListener to the button
        button.addActionListener(this);

        // create a new JPanel with BorderLayout
        JPanel buddyPanel = new JPanel(new BorderLayout());

        // create a GridLayout
        GridLayout gl = new GridLayout(3, 2);

        // set the GridLayout vertical gaps
        gl.setVgap(2);

        // create a new JPanel with GridLayout
        JPanel buddyInfoPanel = new JPanel(gl);


        // add the JLabels and JTextArea instances to the buddyInfoPanel
        buddyInfoPanel.add(new JLabel(text.NAME.name()));
        buddyInfoPanel.add(this.name);
        buddyInfoPanel.add(new JLabel(text.ADDRESS.name()));
        buddyInfoPanel.add(this.address);
        buddyInfoPanel.add(new JLabel(text.PHONENUM.name()));
        buddyInfoPanel.add(this.number);


        // add the button to the buddyPanel at the bottom
        buddyPanel.add(button, BorderLayout.SOUTH);

        // add the buddyInfoPanel to the buddyPanel at the center
        buddyPanel.add(buddyInfoPanel, BorderLayout.CENTER);

        // add the buddyPanel to the buddyFrame
        removeFrame.add(buddyPanel);

        // set the location of the buddyFrame to be at FRAMEDIMENSION[0], FRAMEDIMENSION[1]
        removeFrame.setLocation(200, 400);

        // set buddyFrame visible
        removeFrame.setVisible(true);
    }


    /*private void writeTOfile(BuddyInfo [] bIarray)
    {
        // try to create and write to a file
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME));

            out.write(ADDRESSBOOKINFO);                   // write to file opening line

            // for every BuddyInfo in array write to file
            for(BuddyInfo bI: bIarray)
            {
                String contact = "\n" + bI.getName() + "\n" + bI.getAddress() + "\n" + bI.getPhoneNum() + "\n";
                out.write(contact);
            }

            out.close();           // close the file
        }

        // catch an IOException and display message
        catch (IOException io)
        {
            System.out.println("Cannot create the file to write in.");
        }

    }*/

    public static void main(String[] args){
        /*BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);*/
        AddressBookView example = new AddressBookView("GUI Address Book");
    }
}
