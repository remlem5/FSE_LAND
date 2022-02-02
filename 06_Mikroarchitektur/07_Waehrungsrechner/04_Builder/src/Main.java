import ausgangspunkt.WR;
import meins.EUR2CHF;

public class Main {

    public static void main(String[] args) {

        double betrag = 12.74;

        WR wr1 = new WR.WRBuilder(new EUR2CHF()).betrag(betrag).build();

        System.out.println(wr1.umrechnen(betrag));

        WR wr2 = new WR.WRBuilder(new EUR2CHF()).betrag(betrag).gebuehr(0.15).build();

        System.out.println(wr2.umrechnen(betrag));

    }
}
