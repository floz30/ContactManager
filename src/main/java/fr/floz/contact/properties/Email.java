package fr.floz.contact.properties;

/**
 * Représente une adresse mail électronique.
 */
public class Email implements Properties {
    public enum Type {
        HOME,
        WORK
    }

    private final String email;
    private final boolean isPref;
    private final Type type;

    private Email(EmailBuilder builder) {
        this.email = builder.email;
        this.isPref = builder.isPref;
        this.type = builder.type;
    }

    @Override
    public String toString() {
        return email+ "(pref=" +isPref+ "; type=" +type+ ")";
    }

    @Override
    public String toVCF() {
        var sb = new StringBuilder("EMAIL");
        if (isPref) sb.append(";PREF=1");
        if (type != null) sb.append(";TYPE=").append(type);
        sb.append(":").append(email);
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return email.isEmpty();
    }

    public static class EmailBuilder {
        private String email;
        private boolean isPref;
        private Type type;


        public Email build() {
            if (email == null || email.isEmpty()) {
                throw new IllegalStateException("email can't be empty");
            }
            return new Email(this);
        }

        public EmailBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public EmailBuilder setPref() {
            this.isPref = true;
            return this;
        }

        public EmailBuilder setType(Type type) {
            this.type = type;
            return this;
        }
    }
}
