import observer.Lehrer;
import observer.LehrerInAusbildung;
import observer.Schueler;
import observer.Unterricht;

public class Main {

    public static void main(String[] args) {

        Unterricht fse = new Lehrer();
        Schueler tom = new Schueler("Tom");
        Schueler melmi = new Schueler("Melmi");
        Schueler martin = new Schueler("Martin");
        fse.teilnehmen(tom);
        fse.teilnehmen(melmi);
        fse.teilnehmen(martin);

        fse.neuesThema("OO Analyse");

        LehrerInAusbildung sepp = new LehrerInAusbildung("Sepp");

        fse.teilnehmen(sepp);
        fse.nachHauseGehen(martin);

        fse.neuesThema("Mikroarchitektur");

    }
}
