package ausgangspunkt;

public abstract class WR implements IUmrechnen, ISammelumrechnung{

    @Override
    public double umrechnen(double betrag) {
        return betrag * this.getKurs();
    }

    @Override
    public double sammelumrechnen(double[] betraege) {
        double sum = 0.0;
        for (double d: betraege){
            sum += this.umrechnen(d);
        }
        return sum;
    }

    public abstract double getKurs();
}
