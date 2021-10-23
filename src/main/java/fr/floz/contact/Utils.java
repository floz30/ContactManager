package fr.floz.contact;

import java.util.List;

public class Utils {

    private static final List<String> phoneType = List.of("CELL", "HOME", "WORK");
    private static final List<String> emailType = List.of("HOME", "WORK");


    public static String getType(String value) {
        var valueUpper = value.toUpperCase();
        if (phoneType.contains(valueUpper)) {
            return valueUpper;
        } else {
            return "X-" + value;
        }
    }
}
