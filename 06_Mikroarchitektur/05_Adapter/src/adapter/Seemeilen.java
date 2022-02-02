package adapter;

public class Seemeilen implements UmrechnerAdapter{
    @Override
    public double getLaenge(double mass) {
        return mass * 0.000539957;
    }
}
