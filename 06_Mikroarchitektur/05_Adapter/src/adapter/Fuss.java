package adapter;

public class Fuss implements UmrechnerAdapter{
    @Override
    public double getLaenge(double mass) {
        return mass * 3.28084;
    }
}
