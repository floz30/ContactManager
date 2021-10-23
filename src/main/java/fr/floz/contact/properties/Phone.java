package fr.floz.contact.properties;

import fr.floz.contact.Utils;

import java.util.StringJoiner;

/**
 * CELL = Mobile
 * HOME = Perso
 * WORK = Pro
 * X-Etranger = Etranger
 */
public class Phone implements Properties {
    private String numbers = "";

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toVCF() {
        var sj = new StringJoiner("\n");
        var tokensNumbers = numbers.split(";");
        for (var value : tokensNumbers) {
            var tokensData = value.split(":");
            var line = "TEL;" + Utils.getType(tokensData[0])+ ":" +tokensData[1];
            sj.add(line);
        }
        return sj.toString();
    }

    @Override
    public boolean isEmpty() {
        return numbers.isEmpty();
    }
}
