package rechner;

public class Modulo {

    public int rechnen(int zahl1, int zahl2, String zeichen) {
        if (zeichen.equals("%")) {
            return zahl1 % zahl2;
        } else {
            System.out.println("Rechenzeichen wurde nicht erkannt");
            return 0;
        }

    }
}
