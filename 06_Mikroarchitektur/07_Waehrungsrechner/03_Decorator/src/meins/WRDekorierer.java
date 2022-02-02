package meins;

import ausgangspunkt.WR;

public abstract class WRDekorierer extends WR {

    public double addGebuehrVariabel(double betrag){
        return betrag * 1.005;
    }

    public double addGebuehrFix(double betrag){
        return betrag + 5;
    }

    public abstract WR getWr();

}
