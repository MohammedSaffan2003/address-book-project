import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Your main class representing the Address Book GUI
public class AddressBookGUI extends JFrame {

    // Constructor for the AddressBookGUI class
    public AddressBookGUI() {
        initializeUI(); // Call a method to set up the initial user interface
    }

    // Method to initialize the user interface
    private void initializeUI() {
        setTitle("Address Book Application"); // Set the title of the window
        setSize(600, 500); // Set the dimensions of the window (width, height)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when you close the window
        setLocationRelativeTo(null); // Center the window on the screen

        // Components for the user interface
        JLabel nameLabel = new JLabel("Name:"); // Label for the name field
        JTextField nameField = new JTextField(); // Text field for entering the name

        JLabel phoneLabel = new JLabel("Phone:"); // Label for the phone field
        JTextField phoneField = new JTextField(); // Text field for entering the phone number

        JLabel emaiLabel = new JLabel("Email:"); // Label for the email field
        JTextField emailField = new JTextField(); // Text field for entering the email address

        JLabel addressLabel = new JLabel("Address:"); // Label for the adsdress
        JTextField addressField = new JTextField(); // Text field

        JButton addButton = new JButton("Add Contact"); // Button to add a contact
        JButton viewButton = new JButton("View Contacts"); // Button to view contacts

        // Layout manager for the JFrame
        setLayout(new GridLayout(5, 2)); // Use a 5x2 grid layout
        add(nameLabel); // Add the name label
        add(nameField); // Add the name text field
        add(phoneLabel); // Add the phone label
        add(phoneField); // Add the phone text field
        add(emaiLabel);
        add(emailField);
        add(addressLabel); // Add the address label
        add(addressField); // Add the address text field
        add(addButton); // Add the "Add Contact" button
        add(viewButton); // Add the "View Contacts" button

        // Event handling for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a pop-up message when "Add Contact" button is clicked
                JOptionPane.showMessageDialog(AddressBookGUI.this, "Contact added!");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a pop-up message when "View Contacts" button is clicked
                JOptionPane.showMessageDialog(AddressBookGUI.this, "Viewing contacts...");
            }
        });

        setVisible(true); // Make the window visible
    }

    // Main method, the entry point of the Java application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure GUI-related tasks are processed on
        // the event dispatch thread
        SwingUtilities.invokeLater(() -> new AddressBookGUI());
    }
}
