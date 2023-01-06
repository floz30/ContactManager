package fr.floz.contact.properties;

import java.util.Calendar;

/**
 * Représente la date de naissance du contact.
 */
public class Birth implements Properties {
    /**
     * Jour de naissance.
     */
    private final int day;
    /**
     * Mois de naissance.
     */
    private final int month;


    private Birth(BirthBuilder builder) {
        this.day = builder.day;
        this.month = builder.month;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        return day+ "/" +month;
    }

    @Override
    public String toVCF() {
        return "BDAY:--" + String.format("%02d", month) + "" + String.format("%02d", day);
    }

    @Override
    public boolean isEmpty() {
        return day == 0 || month == 0;
    }

    public static class BirthBuilder {
        private int day;
        private int month;

        public Birth build() {
            if (day == 0) {
                throw new IllegalArgumentException("day must be specified");
            }
            if (month == 0) {
                throw new IllegalArgumentException("month must be specified");
            }

            // Check if date is valid
            new Calendar.Builder().setLenient(false).setDate(2024, month - 1, day).build();

            return new Birth(this);
        }

        public BirthBuilder setDay(int day) {
            this.day = day;
            return this;
        }

        public BirthBuilder setMonth(int month) {
            this.month = month;
            return this;
        }
    }

}
