package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestKinoverwaltung {

    @Mock
    private KinoSaal saal = Mockito.mock(KinoSaal.class);
    private KinoSaal saal2 = Mockito.mock(KinoSaal.class);

    private Zeitfenster zeitfenster = Zeitfenster.ABEND;
    private Zeitfenster zeitfenster2 = Zeitfenster.NACHT;
    private KinoVerwaltung kv1 = new KinoVerwaltung();
    private LocalDate localDate = LocalDate.now();
    private Vorstellung v1 = new Vorstellung(saal, zeitfenster, localDate, "Die Festung", 12.5F);
    private Vorstellung v2 = new Vorstellung(saal2, zeitfenster2, localDate, "Simpsons", 14.7F);


    @BeforeEach
    void setup(){
        kv1.einplanenVorstellung(v1);
    }

    @Test
    void testEinplanenVorstellung(){
        List<Vorstellung> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        kv1.einplanenVorstellung(v2);
        Assertions.assertEquals(list, kv1.getVorstellungen());
    }

    @Test
    void testGetVorstellung(){
        List<Vorstellung> list = new ArrayList<>();
        list.add(v1);
        Assertions.assertEquals(list, kv1.getVorstellungen());
    }

}
