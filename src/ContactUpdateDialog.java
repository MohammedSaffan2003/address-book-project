import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactUpdateDialog extends JDialog {
    private AddressBookManager addressBookManager;
    private Contact existingContact;

    // Constructor for the ContactUpdateDialog class
    public ContactUpdateDialog(AddressBookManager addressBookManager, Contact existingContact) {
        this.addressBookManager = addressBookManager;
        this.existingContact = existingContact;

        initializeUI(); // Call a method to set up the initial user interface
    }

    // Method to initialize the user interface
    private void initializeUI() {
        setTitle("Update Contact"); // Set the title of the dialog
        setSize(400, 300); // Set the dimensions of the dialog (width, height)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Define what happens when you close the dialog
        setLocationRelativeTo(null); // Center the dialog on the screen

        // Components for the user interface
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(existingContact.getName());

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(existingContact.getEmail());

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(existingContact.getAddress());

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(existingContact.getCity());

        JLabel stateLabel = new JLabel("State:");
        JTextField stateField = new JTextField(existingContact.getState());

        JLabel pinLabel = new JLabel("Pincode:");
        JTextField pinField = new JTextField(existingContact.getPincode());

        JLabel noteLabel = new JLabel("Important Note:");
        JTextArea noteTextArea = new JTextArea(existingContact.getNote());

        // Layout manager for the dialog
        setLayout(new GridLayout(8, 2));

        // Add components to the layout
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(addressLabel);
        add(addressField);
        add(cityLabel);
        add(cityField);
        add(stateLabel);
        add(stateField);
        add(pinLabel);
        add(pinField);
        add(noteLabel);
        add(noteTextArea);

        // Add an "Update" button with ActionListener
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve updated values from the text fields
                String updatedName = nameField.getText();
                String updatedEmail = emailField.getText();
                String updatedAddress = addressField.getText();
                String updatedCity = cityField.getText();
                String updatedState = stateField.getText();
                String updatedPincode = pinField.getText();
                String updatedNote = noteTextArea.getText();

                // Create a new Contact object with the updated information
                Contact updatedContact = new Contact(
                        updatedName,
                        existingContact.getPhone(), // Phone number remains the same
                        updatedEmail,
                        updatedAddress,
                        updatedCity,
                        updatedState,
                        updatedPincode,
                        updatedNote);

                // Call a method in your AddressBookManager to update the contact in the
                // database
                addressBookManager.updateContact(updatedContact);

                // Close the dialog
                dispose();
            }
        });

        // Add the "Update" button to the layout
        add(updateButton);

        setVisible(true); // Make the dialog visible
    }
}
