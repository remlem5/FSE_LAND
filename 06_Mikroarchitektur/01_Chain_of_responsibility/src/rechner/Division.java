package rechner;

public class Division {

    public int rechnen(int zahl1, int zahl2, String zeichen) {
        if (zeichen.equals("/")) {
            return zahl1 / zahl2;
        } else {
            Multiplikation mul = new Multiplikation();
            return mul.rechnen(zahl1, zahl2, zeichen);
        }

    }

}
