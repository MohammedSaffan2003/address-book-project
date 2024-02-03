import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddressBookGUI extends JFrame {
    private AddressBookManager addressBookManager;

    // Constructor for the AddressBookGUI class
    public AddressBookGUI() {
        initializeUI(); // Call a method to set up the initial user interface

        // Initialize the AddressBookManager
        String jdbcUrl = "jdbc:mysql://localhost:3306/ContactManager";
        String dbUser = "root";
        String dbPassword = "HaveIBeenHacked";

        addressBookManager = new AddressBookManager(jdbcUrl, dbUser, dbPassword);
    }

    // Method to initialize the user interface
    private void initializeUI() {
        setTitle("Address Book Application"); // Set the title of the window
        setSize(600, 500); // Set the dimensions of the window (width, height)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when you close the window
        setLocationRelativeTo(null); // Center the window on the screen

        // Components for the user interface
        JLabel nameLabel = new JLabel("Name :"); // Label for the name field
        JTextField nameField = new JTextField(); // Text field for entering the name

        JLabel phoneLabel = new JLabel("Phone :"); // Label for the phone field
        JTextField phoneField = new JTextField(); // Text field for entering the phone number

        JLabel emaiLabel = new JLabel("Email :"); // Label for the email field
        JTextField emailField = new JTextField(); // Text field for entering the email address

        JLabel addressLabel = new JLabel("Address :"); // Label for the address
        JTextField addressField = new JTextField(); // Text field

        JLabel cityLabel = new JLabel("City :"); // Label for the address
        JTextField cityField = new JTextField(); // Text field

        JLabel stateLabel = new JLabel("State :"); // Label for the address
        JTextField stateField = new JTextField(); // Text field

        JLabel pinLabel = new JLabel("Pincode :");
        JTextField pinField = new JTextField();

        JLabel noteLabel = new JLabel("Important Note :");
        JTextArea noteTextArea = new JTextArea();

        JButton addButton = new JButton("Add Contact"); // Button to add a contact
        JButton viewButton = new JButton("View Contacts"); // Button to view contacts

        JButton updateButton = new JButton("Update Contact"); // Button to update a contact
        JButton deleteButton = new JButton("Delete Contact"); // Button to delete a contact

        // Layout manager for the JFrame
        setLayout(new GridLayout(10, 2)); // Use a 5x2 grid layout
        add(nameLabel); // Add the name label
        add(nameField); // Add the name text field
        add(phoneLabel); // Add the phone label
        add(phoneField); // Add the phone text field
        add(emaiLabel);
        add(emailField);
        add(addressLabel); // Add the address label
        add(addressField); // Add the address text field
        add(cityLabel);
        add(cityField);
        add(stateLabel); // Add the state label
        add(stateField); // Add the state text field
        add(pinLabel);
        add(pinField);
        add(noteLabel);
        add(noteTextArea);

        add(addButton); // Add the "Add Contact" button
        add(viewButton); // Add the "View Contacts" button
        add(updateButton); // Add the "Update Contact" button
        add(deleteButton); // Add the "Delete Contact" button

        // Event handling for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a Contact object with the entered information
                Contact newContact = new Contact(
                        nameField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        addressField.getText(),
                        cityField.getText(),
                        stateField.getText(),
                        pinField.getText(),
                        noteTextArea.getText());

                // Add the contact to the AddressBookManager
                addressBookManager.addContact(newContact);

                // Show a pop-up message when "Add Contact" button is clicked
                JOptionPane.showMessageDialog(AddressBookGUI.this, "Contact added:\n" + newContact.toString());
            }
        });

        // Event handling for the buttons
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the list of contacts from the AddressBookManager
                List<Contact> contacts = addressBookManager.getAllContacts();

                // Create a string representation of the contacts
                StringBuilder contactsText = new StringBuilder("Contacts:\n");
                for (Contact contact : contacts) {
                    contactsText.append(contact.toString()).append("\n");
                }

                // Display the contacts in a new dialog
                JOptionPane.showMessageDialog(AddressBookGUI.this, contactsText.toString(), "View Contacts",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Event handling for the buttons

        // Event handling for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the user for the phone number to identify the contact
                String phoneNumber = JOptionPane.showInputDialog(AddressBookGUI.this, "Enter Phone Number to Update:");

                // Retrieve the contact based on the phone number
                Contact existingContact = addressBookManager.getContactByPhone(phoneNumber);

                if (existingContact != null) {
                    // Create a new dialog for updating the contact details
                    JDialog updateDialog = new ContactUpdateDialog(addressBookManager, existingContact);

                    // Set the dialog to be visible
                    updateDialog.setVisible(true);
                } else {
                    // Show a pop-up message if the contact with the provided phone number doesn't
                    // exist
                    JOptionPane.showMessageDialog(AddressBookGUI.this,
                            "Contact not found for the provided phone number.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the user for the phone number to identify the contact
                String phoneNumber = JOptionPane.showInputDialog(AddressBookGUI.this, "Enter Phone Number to Delete:");

                // Delete the contact based on the phone number
                boolean contactDeleted = addressBookManager.deleteContactByPhone(phoneNumber);

                if (contactDeleted) {
                    // Show a pop-up message indicating that the contact is deleted
                    JOptionPane.showMessageDialog(AddressBookGUI.this, "Contact deleted successfully.");
                } else {
                    // Show a pop-up message if the contact with the provided phone number doesn't
                    // exist
                    JOptionPane.showMessageDialog(AddressBookGUI.this,
                            "Contact not found for the provided phone number.");
                }
            }
        });

        setVisible(true); // Make the window visible
    }

    // Main method, the entry point of the Java application
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // Usings SwingUtilities.invokeLater to ensure GUI-related tasks are processed
        // on
        // the event dispatch thread
        String jdbcUrl = "jdbc:mysql://localhost:3306/ContactManager";
        String dbUser = "root";
        String dbPassword = "HaveIBeenHacked";

        AddressBookManager addressBookManager = new AddressBookManager(jdbcUrl, dbUser, dbPassword);
        SwingUtilities.invokeLater(() -> new AddressBookGUI());
    }
}
