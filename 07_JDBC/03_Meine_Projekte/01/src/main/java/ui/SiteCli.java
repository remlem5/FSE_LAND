package ui;

import dataaccess.SqlSiteRepo;
import domain.Site;

import java.util.List;
import java.util.Scanner;

public class SiteCli {

    Scanner scan;
    SqlSiteRepo repo;

    public SiteCli(SqlSiteRepo repo){
        this.scan = new Scanner(System.in);
        this.repo = repo;
    }

    private void showMenue(){
        System.out.println("----------- BAUSTELLEN ------------");
        System.out.println("(1) Alle Baustellen anzeigen");
        System.out.println("(x) ENDE");
    }

    public void start(){
        String input = "-";
        while (!input.equals("x")){
            showMenue();
            input = scan.nextLine();
            switch (input){
                case "1":
                    showAllSites();
                    break;
                default:
                    inputError();
                    break;
            }
        }
    }

    private void showAllSites() {
        List<Site> list = null;

        list = repo.getAll();
        if (list.size() > 0){
            for (Site site : list){
                System.out.println(site);
            }
        } else {
            System.out.println("Keine Baustelle vorhanden!");
        }
    }

    private void inputError() {
        System.out.println("Bitte nur gültige Zahlen eingeben!");
    }

}
