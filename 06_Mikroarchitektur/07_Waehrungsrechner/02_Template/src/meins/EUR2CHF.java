package meins;

import ausgangspunkt.WR;

public class EUR2CHF extends WR {

    double kurs = 1.04;

    public EUR2CHF() {
    }

    @Override
    public double getKurs() {
        return this.kurs;
    }
}
