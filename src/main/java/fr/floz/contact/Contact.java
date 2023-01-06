package fr.floz.contact;

import fr.floz.contact.properties.Birth;
import fr.floz.contact.properties.Email;
import fr.floz.contact.properties.Name;
import fr.floz.contact.properties.Phone;

import java.util.List;
import java.util.StringJoiner;

public class Contact {
    private Name name;
    private Birth birth;
    private Phone phone;
    private List<Email> emails;


    public Contact() {

    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
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
        if (phone != null && !phone.isEmpty()) sb.add(phone.toVCF());
        if (emails != null && !emails.isEmpty()) emails.forEach(e -> sb.add(e.toVCF()));
        if (birth != null && !birth.isEmpty()) sb.add(birth.toVCF());
        sb.add("END:VCARD");
        return sb.toString();
    }
}
