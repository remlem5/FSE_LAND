package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TestVorstellung {

    private Vorstellung vorstellung;
    private String film = "Interstellar";
    private float preis = 12.5F;
    private Zeitfenster zeitfenster = Zeitfenster.ABEND;
    private LocalDate localDate = LocalDate.now();
    private Map<Character, Integer> map = new HashMap<>();
    private KinoSaal kinoSaal = new KinoSaal("ks1", map);

    @BeforeEach
    void setup(){
        map.put('A',2);
        map.put('B',4);

        vorstellung = new Vorstellung(kinoSaal, zeitfenster, localDate, film, preis);
    }

    @Test
    void testName(){
        Assertions.assertEquals("Interstellar", vorstellung.getFilm());
        Assertions.assertNotEquals("Simpsons", vorstellung.getFilm());
    }

    @Test
    void testSaal(){
        Assertions.assertEquals(kinoSaal, vorstellung.getSaal());
    }

    @Test
    void testZeitfenster(){
        Assertions.assertEquals(zeitfenster, vorstellung.getZeitfenster());
    }

    @Test
    void testDatum(){
        Assertions.assertEquals(localDate, vorstellung.getDatum());
    }

    @Test
    void testKaufeTicket(){
        Assertions.assertEquals(vorstellung.kaufeTicket('A', 2, 25F).toString(),
                new Ticket(kinoSaal.getName(), zeitfenster, localDate, 'A', 2).toString());
    }

    @Test
    void testEquals(){
        Assertions.assertTrue(vorstellung.equals(vorstellung));
    }

}
