package fr.floz.contact.properties;

import fr.floz.contact.Utils;

import java.util.StringJoiner;

public class Email implements Properties {
    private String emails = "";

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    @Override
    public String toVCF() {
        var sj = new StringJoiner("\n");
        var tokensMail = emails.split(";");
        for (var value : tokensMail) {
            var tokensData = value.split(":");
            var line = "EMAIL;" + Utils.getType(tokensData[0])+ ":" +tokensData[1];
            sj.add(line);
        }
        return sj.toString();
    }

    @Override
    public boolean isEmpty() {
        return emails.isEmpty();
    }
}
