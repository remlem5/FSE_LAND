package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Dieses Beispiel stammt aus https://training.cherriz.de/cherriz-training/1.0.0/testen/junit5.html
 */
public class App 
{
    public static void main( String[] args )
    {
        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        KinoSaal ks = new KinoSaal("LadyX",map);

        //Platz prüfen
        System.out.println(ks.pruefePlatz('A',11));
        System.out.println(ks.pruefePlatz('A',10));
        System.out.println(ks.pruefePlatz('B',10));
        System.out.println(ks.pruefePlatz('C',14));

        //Aufgabe 3
        //Kinosaal anlegen
        Map<Character, Integer> mapNeu = new HashMap<>();
        mapNeu.put('D',4);
        mapNeu.put('E',8);
        mapNeu.put('F',16);
        KinoSaal neuerSaal = new KinoSaal("Neu", mapNeu);

        //Vorstellungen anlegen
        Vorstellung neueVorstellung1 = new Vorstellung(neuerSaal, Zeitfenster.NACHMITTAG, LocalDate.now(), "Zwei wie Pech und Schwefel", 12.5F);
        Vorstellung neueVorstellung2 = new Vorstellung(ks, Zeitfenster.ABEND, LocalDate.now(), "Vier Fäuste gegen Rio", 11.8F);

        //Vorstellungen über Kinoverwaltung einplanen
        KinoVerwaltung kinoVerwaltung = new KinoVerwaltung();
        kinoVerwaltung.einplanenVorstellung(neueVorstellung1);
        kinoVerwaltung.einplanenVorstellung(neueVorstellung2);
        System.out.println(kinoVerwaltung.getVorstellungen());
        Ticket t1 = kinoVerwaltung.kaufeTicket(neueVorstellung1, 'F', 6, 14);
        Ticket t2 = kinoVerwaltung.kaufeTicket(neueVorstellung2, 'A', 8, 20);

        //Tickets ausgeben
        System.out.println(t1);
        System.out.println(t2);

    }
}
