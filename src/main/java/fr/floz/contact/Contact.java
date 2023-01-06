package fr.floz.contact;

import fr.floz.contact.properties.Birth;
import fr.floz.contact.properties.Email;
import fr.floz.contact.properties.Name;
import fr.floz.contact.properties.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Contact {
    private Name name;
    private Birth birth;
    private final List<Phone> phones = new ArrayList<>();
    private final List<Email> emails = new ArrayList<>();


    public Contact() {

    }

    public void setName(Name name) {
        this.name = name;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public void addEmail(Email email) {
        this.emails.add(email);
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public String toVCF() {
        var sb = new StringJoiner("\n");
        sb.add("BEGIN:VCARD");
        sb.add("VERSION:2.1");
        if (name != null && !name.isEmpty()) sb.add(name.toVCF());
        if (!phones.isEmpty()) phones.forEach(p -> sb.add(p.toVCF()));
        if (!emails.isEmpty()) emails.forEach(e -> sb.add(e.toVCF()));
        if (birth != null && !birth.isEmpty()) sb.add(birth.toVCF());
        sb.add("END:VCARD");
        return sb.toString();
    }
}
