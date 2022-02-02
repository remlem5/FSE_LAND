package christbaum;

public class BaumMitKerzen extends ChristbaumDekorierer{

    private final Christbaum christbaum;

    public BaumMitKerzen(Christbaum christbaum){
        this.christbaum = christbaum;
    }

    @Override
    public String getBaum() {
        return christbaum.getBaum() + ", mit Kerzen";
    }
}
