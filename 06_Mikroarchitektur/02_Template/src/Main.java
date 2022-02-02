import rechner.*;

public class Main {

    public static void main(String[] args) {

        int zahl1 = 7;
        int zahl2 = 3;
        String rechenzeichen = "+";

        if (rechenzeichen.equals("+")){
            Addition add = new Addition(zahl1, zahl2);
            System.out.println(add.ergebnis);
        } else if (rechenzeichen.equals("-")){
            Subtraktion sub = new Subtraktion(zahl1, zahl2);
            System.out.println(sub.ergebnis);
        } else if (rechenzeichen.equals("/")){
            Division div = new Division(zahl1, zahl2);
            System.out.println(div.ergebnis);
        } else if (rechenzeichen.equals("*")){
            Multiplikation mul = new Multiplikation(zahl1, zahl2);
            System.out.println(mul.ergebnis);
        } else if (rechenzeichen.equals("%")){
            Modulo mod = new Modulo(zahl1, zahl2);
            System.out.println(mod.ergebnis);
        } else {
            System.out.println("Rechenzeichen wurde nicht erkannt");
        }

    }
}
