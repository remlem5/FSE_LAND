package meins;

import ausgangspunkt.IUmrechnen;

public abstract class WRAdapter implements IUmrechnen {

    @Override
    public abstract double umrechnen(double betrag);

}
