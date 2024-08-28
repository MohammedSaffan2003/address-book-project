# Address Book Manager

## Overview

The Address Book Manager is a desktop application developed using Java Swing and MySQL. It allows users to manage their contacts by providing functionalities to add, view, update, and delete contact details. The application features a user-friendly graphical interface that makes it easy to interact with the contact data stored in a MySQL database.

## Features

- **Add Contact:** Save new contact information such as name, phone number, email, address, city, state, pincode, and a note.
- **View Contacts:** Display all saved contacts in a dialog window.
- **Update Contact:** Modify existing contact details by searching through the phone number.
- **Delete Contact:** Remove a contact from the database using the phone number.

## Technologies Used

- **Java Swing:** For building the graphical user interface (GUI).
- **MySQL:** For storing and managing contact data.
- **JDBC:** Java Database Connectivity, for connecting and executing SQL queries in MySQL.

## Installation

### Prerequisites

- **Java Development Kit (JDK)** installed (version 8 or higher).
- **MySQL** installed and running on your local machine.
- **IDE** (e.g., IntelliJ IDEA, Eclipse) for running the Java code.

### Setup Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/MohammedSaffan2003/address-book-project.git
   cd address-book-manager
### Set Up the MySQL Database:

1. **Create a MySQL database named `ContactManager`.**
2. **Import the provided `schema.sql` file to set up the necessary tables.**

### Configure Database Credentials:

1. **Open `AddressBookGUI.java`.**
2. **Replace the `jdbcUrl`, `dbUser`, and `dbPassword` variables with your MySQL credentials.**

### Run the Application:

1. **Open the project in your preferred IDE.**
2. **Compile and run the `AddressBookGUI` class.**

### Usage

- **Launch the application and use the GUI to manage your contacts.**
- **The main window allows you to add, view, update, and delete contacts.**

#### Adding a Contact

1. **Enter the contact details in the respective fields.**
2. **Click on the "Add Contact" button.**
3. **A confirmation dialog will appear indicating that the contact has been successfully added.**

#### Viewing Contacts

1. **Click on the "View Contacts" button.**
2. **A new dialog will display a list of all contacts.**

#### Updating a Contact

1. **Click on the "Update Contact" button.**
2. **Enter the phone number of the contact you wish to update.**
3. **Modify the displayed contact details in the new dialog.**
4. **Click "Update" to save the changes.**

#### Deleting a Contact

1. **Click on the "Delete Contact" button.**
2. **Enter the phone number of the contact you wish to delete.**
3. **Confirm the deletion, and the contact will be removed from the database.**
