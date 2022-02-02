import ausgangspunkt.WR;
import meins.EUR2CHF;
import meins.WRAdapter;

public class Main {

    public static void main(String[] args) {

        double betrag = 12.74;

        WR wr1 = new EUR2CHF();
        System.out.println(wr1.umrechnen(betrag));
        

    }
}
