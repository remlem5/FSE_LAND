package meins;

import ausgangspunkt.WR;

public class EUR2USD extends WR {

    double kurs = 1.13;
    double gebuehr;

    @Override
    public double umrechnen(double betrag) {
        if (this.gebuehr == 0) {
            return betrag * this.kurs;
        } else {
            return betrag * this.kurs + (1 + this.gebuehr);
        }
    }

//    @Override
//    public double sammelumrechnen(double[] betraege) {
//        double sum = 0;
//        for (double betrag :betraege) {
//            sum += umrechnen(betrag);
//        }
//        return sum;
//    }
}
