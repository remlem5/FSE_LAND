package christbaum;

public class BaumMitLametta extends ChristbaumDekorierer{

    private final Christbaum christbaum;

    public BaumMitLametta(Christbaum christbaum){
        this.christbaum = christbaum;
    }

    @Override
    public String getBaum() {
        return christbaum.getBaum() + ", mit Lametta";
    }
}
