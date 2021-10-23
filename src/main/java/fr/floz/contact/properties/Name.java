package fr.floz.contact.properties;

public class Name implements Properties {
    private String lastName = "";
    private String firstName = "";
    private String middleName = "";
    private String title = "";

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toVCF() {
        return "N:" +lastName+ ";" +firstName+ ";" +middleName+ ";" +title;
//        if (!lastName.isEmpty() || !firstName.isEmpty() || !middleName.isEmpty() || !title.isEmpty()) {
//
//        }
//        return "";
    }

    @Override
    public boolean isEmpty() {
        return lastName.isEmpty() && firstName.isEmpty() && middleName.isEmpty() && title.isEmpty();
    }
}
