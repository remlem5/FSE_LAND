import ausgangspunkt.WR;
import meins.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String variante = "EUR2USD";
        String variante2 = "EUR2CHF";
        double betrag = 12.78;

//        List<WR> wrs =  new ArrayList<>();
//
//        wrs.add(new EUR2CHF());
//        wrs.add(new EUR2YEN());
//        wrs.add(new EUR2USD());

//        for (WR wr : wrs){
//            if(wr.umrechnen(variante, betrag) > 0){
//                System.out.println(wr.umrechnen(variante, betrag));
//            }
//        }
//        for (WR wr : wrs){
//            if(wr.umrechnen(variante2, betrag) > 0){
//                System.out.println(wr.umrechnen(variante2, betrag));
//            }
//        }
//        System.out.println();

        List<WRDekorierer> deklist = new ArrayList<>();

        WRDekorierer wrd = new WRDekoriererImpl(new EUR2CHF());
        WRDekorierer wrd1 = new WRDekoriererImpl(new EUR2USD());
        WRDekorierer wrd2 = new WRDekoriererImpl(new EUR2YEN());

        deklist.add(wrd);
        deklist.add(wrd1);
        deklist.add(wrd2);

        for (WRDekorierer dek : deklist){
            if (dek.getWr().umrechnen(variante, betrag) > 0){
                System.out.println(dek.getWr().umrechnen(variante, betrag));
                System.out.println(dek.addGebuehrVariabel(dek.getWr().umrechnen(variante, betrag)));
            }
        }

        for (WRDekorierer dek : deklist){
            if (dek.getWr().umrechnen(variante2, betrag) > 0){
                System.out.println(dek.getWr().umrechnen(variante2, betrag));
                System.out.println(dek.addGebuehrVariabel(dek.getWr().umrechnen(variante2, betrag)));
            }
        }

        for (WRDekorierer dek : deklist){
            if (dek.getWr().umrechnen(variante, betrag) > 0){
                System.out.println(dek.getWr().umrechnen(variante, betrag));
                System.out.println(dek.addGebuehrFix(dek.getWr().umrechnen(variante, betrag)));
            }
        }

        for (WRDekorierer dek : deklist){
            if (dek.getWr().umrechnen(variante2, betrag) > 0){
                System.out.println(dek.getWr().umrechnen(variante2, betrag));
                System.out.println(dek.addGebuehrFix(dek.getWr().umrechnen(variante2, betrag)));
            }
        }

    }
}
