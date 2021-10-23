package fr.floz.contact.properties;

public class Birth implements Properties {
    private int day;
    private int month;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toVCF() {
        return "BDAY:--" +month+ "-" +day;
    }

    @Override
    public boolean isEmpty() {
        return day == 0 || month == 0;
    }
}
