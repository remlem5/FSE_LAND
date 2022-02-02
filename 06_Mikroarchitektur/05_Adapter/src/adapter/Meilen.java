package adapter;

public class Meilen implements Umrechner{
    @Override
    public double getLaenge(double mass) {
        return mass * 0.000621371;
    }
}
