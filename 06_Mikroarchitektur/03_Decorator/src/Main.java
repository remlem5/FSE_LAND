import christbaum.BaumMitKerzen;
import christbaum.BaumMitKugeln;
import christbaum.BaumMitLametta;

public class Main {

    public static void main(String[] args) {

        BaumMitKugeln bMKu = new BaumMitKugeln();
        System.out.println(bMKu.getBaum());

        BaumMitKerzen bMKe = new BaumMitKerzen(new BaumMitKugeln());
        System.out.println(bMKe.getBaum());

        BaumMitLametta bML = new BaumMitLametta(new BaumMitKugeln());
        System.out.println(bML.getBaum());

        BaumMitLametta bMA = new BaumMitLametta(new BaumMitKerzen(new BaumMitKugeln()));
        System.out.println(bMA.getBaum());

        BaumMitKerzen bMA2 = new BaumMitKerzen(new BaumMitLametta(new BaumMitKugeln()));
        System.out.println(bMA2.getBaum());

    }
}
