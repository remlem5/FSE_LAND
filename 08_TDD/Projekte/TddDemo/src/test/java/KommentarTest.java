import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KommentarTest {



    @Test
    public void testAnlegen_korrekteInput_Parameter_kommentarAngelegt(){
        //Given
        String kommentartext = "Kommentar-Text";
        String autor = "Melmi";
        int bewertung = 3;
        //int stimmen = 0;

        //When
        Kommentar k1 = new Kommentar(kommentartext, autor, bewertung);

        //Then
        Assertions.assertEquals("Kommentar-Text", k1.getKommentarText());
        Assertions.assertEquals(0, k1.getStimmen());
        Assertions.assertEquals(3, k1.getBewertung());
        Assertions.assertEquals("Melmi", k1.getAutor());
    }
}
