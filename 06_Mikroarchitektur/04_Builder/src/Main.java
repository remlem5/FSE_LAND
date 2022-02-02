import builder.Artikel;

public class Main {

    public static void main(String[] args) {

        Artikel artikel1 = new Artikel.Artikelbuilder(1, "Schraube").build();
        System.out.println(artikel1);

        Artikel artikel2 = new Artikel.Artikelbuilder(2, "Nagel").lagerort("Kleinteilelager").build();
        System.out.println(artikel2);

        Artikel artikel3 = new Artikel.Artikelbuilder(3, "Rigips-Platte").nummer("RP1234").lagerort("Außenlager").zusatzInfo("2,0m x 1,5m").build();
        System.out.println(artikel3);

    }
}
