import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBookManager {
    private static Connection connection = null;

    public AddressBookManager(String jdbcUrl, String dbUser, String dbPassword) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Create the necessary table if it doesn't exist
            createTableIfNotExists();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public AddressBookManager() {
        // TODO Auto-generated constructor stub
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (id INT AUTO_INCREMENT UNIQUE, " +
                "name VARCHAR(255) NOT NULL, phone VARCHAR(20) PRIMARY KEY, email VARCHAR(255) , address VARCHAR(255), "
                +
                "city VARCHAR(255), state VARCHAR(255), pincode VARCHAR(10), note TEXT)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the 'contacts' table.");
        }
    }

    public void addContact(Contact contact) {
        String insertSQL = "INSERT INTO contacts (name, phone, email, address, city, state, pincode, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getAddress());
            preparedStatement.setString(5, contact.getCity());
            preparedStatement.setString(6, contact.getState());
            preparedStatement.setString(7, contact.getPincode());
            preparedStatement.setString(8, contact.getNote());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add a contact to the database.");
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String selectSQL = "SELECT * FROM contacts";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("pincode"),
                        resultSet.getString("note"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve contacts from the database.");
        }

        return contacts;
    }

    public Contact getContactByPhone(String phoneNumber) {
        String selectSQL = "SELECT * FROM contacts WHERE phone = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Contact(
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getString("address"),
                            resultSet.getString("city"),
                            resultSet.getString("state"),
                            resultSet.getString("pincode"),
                            resultSet.getString("note"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve contact by phone from the database.");
        }
        return null; // Return null if the contact is not found
    }

    public boolean deleteContactByPhone(String phoneNumber) {
        String deleteSQL = "DELETE FROM contacts WHERE phone = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, phoneNumber);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if any row is affected (contact deleted), false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete contact by phone from the database.");
        }
    }

    public void updateContact(Contact updatedContact) {
        // Write the SQL query to update the contact in the database
        String updateSQL = "UPDATE contacts SET name=?, email=?, address=?, city=?, state=?, pincode=?, note=? WHERE phone=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, updatedContact.getName());
            preparedStatement.setString(2, updatedContact.getEmail());
            preparedStatement.setString(3, updatedContact.getAddress());
            preparedStatement.setString(4, updatedContact.getCity());
            preparedStatement.setString(5, updatedContact.getState());
            preparedStatement.setString(6, updatedContact.getPincode());
            preparedStatement.setString(7, updatedContact.getNote());
            preparedStatement.setString(8, updatedContact.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update contact in the database.");
        }
    }
}
