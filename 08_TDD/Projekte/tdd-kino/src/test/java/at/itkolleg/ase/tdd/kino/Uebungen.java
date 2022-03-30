package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.opentest4j.AssertionFailedError;

import java.time.LocalDate;
import java.util.*;

public class Uebungen {

    Map<Character, Integer> map = new HashMap<>();
    KinoSaal saal = new KinoSaal("Saal1", map);
    Vorstellung v1, v2;
    KinoVerwaltung kv1 = new KinoVerwaltung();
    List<Vorstellung> list = new ArrayList<>();
    Ticket t1 = new Ticket(saal.getName(), Zeitfenster.ABEND,
            LocalDate.now(), 'A', 4);
    Ticket t2 = new Ticket(saal.getName(), Zeitfenster.NACHMITTAG,
            LocalDate.now(), 'B', 7);
    public static List<Ticket> ticketList = new ArrayList<>();

    @BeforeEach
    void setup() {
        map.put('A', 4);
        map.put('B', 8);
        v1 = new Vorstellung(saal,
                Zeitfenster.ABEND, LocalDate.now(),
                "Interstellar", 12.5F);
        v2 = new Vorstellung(saal, Zeitfenster.NACHMITTAG,
                LocalDate.now(), "Simpsons", 14F);
        ticketList.add(t1);
        ticketList.add(t2);

    }

    @DisplayName("Anlegen einer Vorstellung!")
    @Test
    void testAnlegenEinerVorstellung() {
        try {
            Assertions.assertInstanceOf(Vorstellung.class, v1);
            System.out.println("Vorstellung erfolgreich angelegt!");
        } catch (AssertionFailedError e) {
            Assertions.fail("Vorstellung nicht angelegt!");
        }
    }

    @Test
    void testAnlegenMehrererVorstellungen() {
        try {
            kv1.einplanenVorstellung(v1);
            kv1.einplanenVorstellung(v2);

            list.add(v1);
            list.add(v2);
            Assertions.assertEquals(list, kv1.getVorstellungen());
            //kv1.einplanenVorstellung(v1);
            Assertions.assertEquals(list, kv1.getVorstellungen());
            System.out.println("Mehrere Vorstellungen erfolgreich angelegt");
        } catch (AssertionFailedError e) {
            Assertions.fail("Something went wrong!");
        }
    }

    @Test
    void testDoppeltesAnlegenEinerVeranstaltung() {
        kv1.einplanenVorstellung(v1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> kv1.einplanenVorstellung(v1));
    }

    @ParameterizedTest
    @CsvSource({"A, 3, 27", "B, 6, 25", "A,4,17", "B,1,30"})
    void testTicketkaeufeRichtigeParameter(char reihe, int platz, float geld) {
        Assertions.assertNotNull(v1.kaufeTicket(reihe, platz, geld));
    }

    @ParameterizedTest
    @CsvSource({"A, 3, 2", "B, 9, 25", "A,0,17", "B,12,30"})
    void testTicketkaeufeFalscheParameter(char reihe, int platz, float geld) {
        try {
            v1.kaufeTicket(reihe, platz, geld);
        } catch (IllegalArgumentException iAE){
            System.out.println("Ticketkauf nicht erfolgreich");
        }
    }


    @TestFactory
    Collection<DynamicTest> testeTickkaufMitZufallswerten() {
        List<DynamicTest> testList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random rand = new Random();
            char randomChar = (char) (rand.nextInt(3) + 'A');
            int randomPlatz = rand.nextInt(10) + 1;
            float randomGeld = rand.nextFloat(20) + 10;
            try {
                Ticket t = v1.kaufeTicket(randomChar, randomPlatz, randomGeld);
                testList.add(DynamicTest.dynamicTest(
                        "Ticket-Kauf erfolgreich",
                        () -> Assertions.assertNotNull(t)));
            } catch (IllegalArgumentException iAE) {
                testList.add(DynamicTest.dynamicTest(
                        "Ticket-Kauf NICHT erfolgreich",
                        () -> Assertions.assertThrows(IllegalArgumentException.class, () -> v1.kaufeTicket(randomChar, randomPlatz, randomGeld))
                ));
            } catch (IllegalStateException iSE) {
                testList.add(DynamicTest.dynamicTest(
                        "Ticket bereits verkauft",
                        () -> Assertions.assertThrows(IllegalStateException.class, () -> v1.kaufeTicket(randomChar, randomPlatz, randomGeld))
                ));
            }
        }
        return testList;
    }

}
