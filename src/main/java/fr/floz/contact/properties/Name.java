package fr.floz.contact.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Représente les composants du nom du contact.
 */
public class Name implements Properties {
    /**
     * Nom(s) de famille.
     */
    private final List<String> lastNames;
    /**
     * Prénom(s).
     */
    private final List<String> firstNames;
    /**
     * Nom(s) additionnel(s).
     */
    private final List<String> additionalNames;
    /**
     * Préfixes du nom: Dr, Pr, Mr., Mrs.
     */
    private final List<String> prefixes;
    /**
     * Suffices du nom
     */
    private final List<String> suffixes;
    /**
     * Nom(s) descriptif(s) ou familier(s) donné(s) à la place ou en addition du nom de la personne.
     * Correspond au(x) surnom(s).
     */
    private final List<String> nicknames;
    /**
     * Chaîne formatée représentant le nom associé au contact.
     */
    private final String formattedName;


    private Name(NameBuilder builder) {
        lastNames = builder.lastNames;
        firstNames = builder.firstNames;
        additionalNames = builder.additionalNames;
        prefixes = builder.prefixes;
        suffixes = builder.suffixes;
        nicknames = builder.nicknames;
        formattedName = builder.formattedName;
    }


    @Override
    public String toString() {
        return prefixes + " " + firstNames + " " + additionalNames + " " + lastNames;
    }

    @Override
    public String toVCF() {
        var result = new StringJoiner("\n");
        if (formattedName != null) result.add("FN:" + formattedName);
        var names = "N:" + String.join(",", lastNames) + ";" +
                String.join(",", firstNames) + ";" +
                String.join(",", additionalNames) + ";" +
                String.join(",", prefixes) + ";" +
                String.join(",", suffixes);
        result.add(names);
        if (!nicknames.isEmpty()) result.add("NICKNAME:" + String.join(",", nicknames));
        return result.toString();
    }

    @Override
    public boolean isEmpty() {
        return lastNames.isEmpty() && firstNames.isEmpty() && additionalNames.isEmpty() && prefixes.isEmpty();
    }


    public static class NameBuilder {
        private List<String> lastNames = new ArrayList<>();
        private List<String> firstNames = new ArrayList<>();
        private List<String> additionalNames = new ArrayList<>();
        private List<String> prefixes = new ArrayList<>();
        private List<String> suffixes = new ArrayList<>();
        private List<String> nicknames = new ArrayList<>();
        private String formattedName = "";


        public Name build() {
            return new Name(this);
        }


        public NameBuilder setLastNames(String... lastNames) {
            Collections.addAll(this.lastNames, lastNames);
            return this;
        }

        public NameBuilder setFirstNames(String... firstNames) {
            Collections.addAll(this.firstNames, firstNames);
            return this;
        }

        public NameBuilder setAdditionalNames(String... additionalNames) {
            Collections.addAll(this.additionalNames, additionalNames);
            return this;
        }

        public NameBuilder setPrefixes(String... prefixes) {
            Collections.addAll(this.prefixes, prefixes);
            return this;
        }

        public NameBuilder setSuffixes(String... suffixes) {
            Collections.addAll(this.suffixes, suffixes);
            return this;
        }

        public NameBuilder setNicknames(String... nicknames) {
            Collections.addAll(this.nicknames, nicknames);
            return this;
        }

        public NameBuilder setFormattedName(String formattedName) {
            this.formattedName = formattedName;
            return this;
        }
    }

}
