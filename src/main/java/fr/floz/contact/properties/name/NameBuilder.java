package fr.floz.contact.properties.name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameBuilder {
    private final List<String> lastNames = new ArrayList<>();
    private final List<String> firstNames = new ArrayList<>();
    private final List<String> additionalNames = new ArrayList<>();
    private final List<String> prefixes = new ArrayList<>();
    private final List<String> suffixes = new ArrayList<>();
    private final List<String> nicknames = new ArrayList<>();
    private String formattedName = "";


    public Name build() {
        return new Name(lastNames, firstNames, additionalNames, prefixes, suffixes, nicknames, formattedName);
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
