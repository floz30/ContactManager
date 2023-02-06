package fr.floz.contact.properties.name;

import fr.floz.contact.properties.Properties;
import lombok.*;

import java.util.List;
import java.util.StringJoiner;

/**
 * Représente les composants du nom du contact.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Name implements Properties {
    /**
     * Nom(s) de famille.
     */
    private List<String> lastNames;
    /**
     * Prénom(s).
     */
    private List<String> firstNames;
    /**
     * Nom(s) additionnel(s).
     */
    private List<String> additionalNames;
    /**
     * Préfixes du nom: Dr, Pr, Mr., Mrs.
     */
    private List<String> prefixes;
    /**
     * Suffices du nom
     */
    private List<String> suffixes;
    /**
     * Nom(s) descriptif(s) ou familier(s) donné(s) à la place ou en addition du nom de la personne.
     * Correspond au(x) surnom(s).
     */
    private List<String> nicknames;
    /**
     * Chaîne formatée représentant le nom associé au contact.
     */
    private String formattedName;



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

}
