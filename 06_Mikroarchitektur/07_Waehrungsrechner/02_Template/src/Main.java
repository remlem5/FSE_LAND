import ausgangspunkt.WR;
import meins.EUR2CHF;
import meins.EUR2USD;
import meins.EUR2YEN;

public class Main {

    public static void main(String[] args) {

        double betrag = 12.78;

        WR wr1 = new EUR2CHF();
        System.out.println(wr1.umrechnen(betrag));

        WR wr2 = new EUR2USD();
        System.out.println(wr2.umrechnen(betrag));

        WR wr3 = new EUR2YEN();
        System.out.println(wr3.umrechnen(betrag));

        double[] betraege = new double[3];
        betraege[0] = 47.89;
        betraege[1] = 2587.61;
        betraege[2] = 0.12;

        System.out.println(wr1.sammelumrechnen(betraege));
        System.out.println(wr2.sammelumrechnen(betraege));
        System.out.println(wr3.sammelumrechnen(betraege));

    }
}
