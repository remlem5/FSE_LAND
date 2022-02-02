import ausgangspunkt.WR;
import meins.EUR2CHF;
import meins.EUR2USD;
import meins.EUR2YEN;

import java.util.Iterator;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String variante = "EUR2USD";
        String variante2 = "EUR2CHF";
        double betrag = 12.78;

        Stack<WR> wrs = new Stack<>();

        wrs.push(new EUR2USD());
        wrs.push(new EUR2YEN());

        for (WR wr : wrs) {
            if (wr.umrechnen(variante, betrag) > 0) {
                System.out.println("Variante1: EUR2USD "+wr.umrechnen(variante, betrag));
            }
        }

        wrs.pop();
        wrs.push(new EUR2CHF());

        for (WR wr : wrs) {
            if (wr.umrechnen(variante2, betrag) > 0) {
                System.out.println("Variante2: EUR2YEN "+wr.umrechnen(variante2, betrag));
            }
        }

        double[] betraege = new double[3];
        betraege[0] = 47.89;
        betraege[1] = 2587.61;
        betraege[2] = 0.12;

        for (WR wr : wrs) {
            if (wr.sammelumrechnen(betraege, variante) > 0) {
                System.out.println("Sammelumrechnen:");
                for (double b : betraege) {
                    System.out.println(wr.umrechnen(variante, b));
                }
            }
        }

    }
}
