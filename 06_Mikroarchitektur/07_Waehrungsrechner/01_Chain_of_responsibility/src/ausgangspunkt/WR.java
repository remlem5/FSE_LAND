package ausgangspunkt;

public abstract class WR implements IUmrechnen, ISammelumrechnung{

    private String name;

    @Override
    public double umrechnen(String variante, double betrag) {
        return 0;
    }

    @Override
    public double sammelumrechnen(double[] betraege, String variante) {
        return 0;
    }
}
