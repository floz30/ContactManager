package fr.floz.contact;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static fr.floz.contact.properties.Birth.BirthBuilder;
import static fr.floz.contact.properties.Name.NameBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactsApplicationTests {

    @Test
    public void contact2VCF() {
        var contact = new Contact();
        contact.setName(new NameBuilder().setFirstNames("Chuck").setLastNames("Norris").build());
        contact.setBirth(new BirthBuilder().setDay(15).setMonth(9).build());
        //contact.setPhone(new Phone("0123456789"));
        //contact.setEmail(new Email("norris@texas.com"));

        var result = contact.toVCF();

        assertEquals("BEGIN:VCARD\nVERSION:2.1\nFN:\nN:Norris;Chuck;;;\nBDAY:--0915\nEND:VCARD", result);
    }

    // https://www.evenx.com/vcard-3-0-format-specification
    // https://datatracker.ietf.org/doc/html/rfc6350
    // https://fr.wikipedia.org/wiki/VCard
    // https://learn.microsoft.com/en-us/openspecs/exchange_server_protocols/ms-oxvcard/63fe5c03-955d-47e9-a67a-eb729f880776


    @Test
    @Tag("name")
    public void name2VCF_Full() {
        var name = new NameBuilder()
                .setLastNames("Stevenson")
                .setFirstNames("John")
                .setAdditionalNames("Philip", "Paul")
                .setPrefixes("Dr.")
                .setSuffixes("Jr.", "M.D.", "A.C.P.")
                .setFormattedName("Dr. John Stevenson")
                .setNicknames("Jo")
                .build();
        var expected = "FN:Dr. John Stevenson\nN:Stevenson;John;Philip,Paul;Dr.;Jr.,M.D.,A.C.P.\nNICKNAME:Jo";

        assertEquals(expected, name.toVCF());
    }

    @Test
    @Tag("name")
    public void name2VCF_2() {
        var name = new NameBuilder()
                .setLastNames("Stevenson")
                .setFirstNames("John")
                .setFormattedName("John Stevenson")
                .build();
        var expected = "FN:John Stevenson\nN:Stevenson;John;;;";

        assertEquals(expected, name.toVCF());
    }

    @Test
    @Tag("name")
    public void name2VCF_3() {
        var name = new NameBuilder()
                .build();
        var expected = "FN:\nN:;;;;";

        assertEquals(expected, name.toVCF());
    }

    @Test
    @Tag("name")
    public void name2VCF_4() {
        var name = new NameBuilder()
                .setLastNames("Doe")
                .setFirstNames("Alpha", "Beta")
                .setAdditionalNames("Charlie", "Delta")
                .setSuffixes("Jr.")
                .build();
        var expected = "FN:\nN:Doe;Alpha,Beta;Charlie,Delta;;Jr.";

        assertEquals(expected, name.toVCF());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_1() {
        var birth = new BirthBuilder()
                .setDay(1)
                .setMonth(1)
                .build();
        var expected = "BDAY:--0101";

        assertEquals(expected, birth.toVCF());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_2() {
        var birth = new BirthBuilder()
                .setDay(24)
                .setMonth(12)
                .build();
        var expected = "BDAY:--1224";

        assertEquals(expected, birth.toVCF());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_3() {
        assertThrows(IllegalArgumentException.class, () -> new BirthBuilder().setDay(24).build());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_4() {
        assertThrows(IllegalArgumentException.class, () -> new BirthBuilder().setMonth(10).build());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_5() {
        assertThrows(IllegalArgumentException.class, () -> new BirthBuilder().setMonth(10).setDay(56).build());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_6() {
        assertThrows(IllegalArgumentException.class, () -> new BirthBuilder().setMonth(13).setDay(56).build());
    }

    @Test
    @Tag("birth")
    public void birth2VCF_7() {
        assertThrows(IllegalArgumentException.class, () -> new BirthBuilder().setMonth(13).setDay(22).build());
    }
}
