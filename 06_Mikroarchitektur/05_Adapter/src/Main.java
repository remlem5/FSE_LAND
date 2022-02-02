import adapter.*;

public class Main {

    public static void main(String[] args) {

        double meter = 2105.48;

        Umrechner umrechner = new Meilen();
        System.out.println(umrechner.getLaenge(meter)+" Meilen");

        UmrechnerAdapter umrechnerFuss = new Fuss();
        System.out.println(umrechnerFuss.getLaenge(meter)+ " Fuss");

        UmrechnerAdapter umrechnerSeemeile = new Seemeilen();
        System.out.println(umrechnerSeemeile.getLaenge(meter)+" Seemeilen");

    }
}
