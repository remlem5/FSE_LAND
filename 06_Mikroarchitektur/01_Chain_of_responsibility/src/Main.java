import rechner.Rechner;

public class Main {

    public static void main(String[] args) {
        Rechner rechner = new Rechner();

        int zahl1 = 7;
        int zahl2 = 3;
        String rechenzeichen = "-";

        System.out.println(rechner.rechnen(zahl1, zahl2, rechenzeichen));
    }
}
