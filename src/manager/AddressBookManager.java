package src.manager;

import src.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBookManager {
    private List<Contact> contacts;

    public AddressBookManager() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
