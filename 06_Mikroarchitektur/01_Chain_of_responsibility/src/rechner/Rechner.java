package rechner;

public class Rechner {

    public int rechnen(int zahl1, int zahl2, String zeichen) {
        if (zeichen.equals("+")) {
            return zahl1 + zahl2;
        } else {
            Subtraktion sub = new Subtraktion();
            return sub.rechnen(zahl1, zahl2, zeichen);
        }

    }
}
