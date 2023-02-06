package fr.floz.contact.service;

import fr.floz.contact.Contact;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {
    private final Map<UUID, Contact> contacts;


    public ContactService() {
        this.contacts = new HashMap<>();
    }


    public void create(Contact contact) {
        contacts.putIfAbsent(contact.getId(), contact);
    }

    public void update(Contact contact) {
        contacts.computeIfPresent(contact.getId(), (id, oldContact) -> contact);
    }

    public Optional<Contact> get(UUID id) {
        return Optional.ofNullable(contacts.get(id));
    }

    public List<Contact> getAll() {
        return List.copyOf(this.contacts.values());
    }

}
