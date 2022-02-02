package rechner;

public class Multiplikation {

    public int rechnen(int zahl1, int zahl2, String zeichen) {
        if (zeichen.equals("*")) {
            return zahl1 * zahl2;
        } else {
            Modulo mod = new Modulo();
            return mod.rechnen(zahl1, zahl2, zeichen);
        }

    }
}
