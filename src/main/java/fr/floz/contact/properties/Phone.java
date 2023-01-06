package fr.floz.contact.properties;

/**
 * Représente un numéro de téléphone.
 */
public class Phone implements Properties {
    public enum Type {
        FAX,
        CELL,
        HOME,
        WORK
    }

    private final String number;
    private final boolean isPref;
    private final Type type;


    private Phone(PhoneBuilder builder) {
        this.number = builder.number;
        this.isPref = builder.isPref;
        this.type = builder.type;
    }

    @Override
    public String toString() {
        return number+ "(pref=" +isPref+ "; type=" +type+ ")";
    }

    @Override
    public String toVCF() {
        var sb = new StringBuilder("TEL;VALUE=TEXT");
        if (isPref) sb.append(";PREF=1");
        if (type != null) sb.append(";TYPE=").append(type);
        sb.append(":").append(number);
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return number.isEmpty();
    }

    public static class PhoneBuilder {
        private String number;
        private boolean isPref;
        private Type type;

        public Phone build() {
            if (number == null || number.isEmpty()) {
                throw new IllegalStateException("phone number can't be empty");
            }
            return new Phone(this);
        }

        public PhoneBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public PhoneBuilder setPref() {
            this.isPref = true;
            return this;
        }

        public PhoneBuilder setType(Type type) {
            this.type = type;
            return this;
        }
    }
}
