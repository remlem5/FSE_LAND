package meins;

import ausgangspunkt.WR;

public class EUR2YEN extends WR {

    double kurs = 129.97;

    @Override
    public double umrechnen(String variante, double betrag) {
        if (variante.equals("EUR2YEN")){
            return betrag * kurs;
        } else {
            return 0;
        }
    }

    @Override
    public double sammelumrechnen(double[] betraege, String variante) {
        for (double betrag:betraege) {
            return this.umrechnen(variante, betrag);
        }
        return 0;
    }
}
