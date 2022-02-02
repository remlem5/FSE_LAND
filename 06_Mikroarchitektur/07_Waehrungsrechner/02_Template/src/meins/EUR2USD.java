package meins;

import ausgangspunkt.WR;

public class EUR2USD extends WR {

    double kurs = 1.13;

    public EUR2USD() {
    }

    @Override
    public double getKurs() {
        return this.kurs;
    }
}
