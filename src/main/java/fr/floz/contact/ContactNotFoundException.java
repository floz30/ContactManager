package fr.floz.contact;

import java.util.UUID;

public class ContactNotFoundException extends RuntimeException {

    ContactNotFoundException(UUID id) {
        super("Contact introuvable : " + id);
    }
}
