package meins;

import ausgangspunkt.WR;

public class WRDekoriererImpl extends WRDekorierer{

    private final WR wr;

    public WRDekoriererImpl(WR wr){
        this.wr = wr;
    }

    @Override
    public double sammelumrechnen(double[] betraege, String variante) {
        return 0;
    }

    @Override
    public double umrechnen(String variante, double betrag) {
        return 0;
    }

    @Override
    public WR getWr(){
        return this.wr;
    }

}
