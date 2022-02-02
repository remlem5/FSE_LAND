package rechner;

public class Subtraktion {

    public int rechnen(int zahl1, int zahl2, String zeichen) {
        if (zeichen.equals("-")) {
            return zahl1 - zahl2;
        } else {
            Division div = new Division();
            return div.rechnen(zahl1, zahl2, zeichen);
        }

    }
}
