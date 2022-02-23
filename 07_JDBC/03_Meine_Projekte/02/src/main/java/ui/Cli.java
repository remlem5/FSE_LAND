package ui;

import dataaccess.MySQLStudentRepository;
import dataaccess.MySQLTeacherRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Cli {

    Scanner scan;

    public Cli() {
        this.scan = new Scanner(System.in);
    }

    public void start() {
        String input = "-";
        while (!input.equals("x")) {
            showMenue();
            input = scan.nextLine();
            switch (input) {
                case "1":
                    try {
                        StudentCli studentCli = new StudentCli(new MySQLStudentRepository());
                        studentCli.start();
                    } catch (SQLException throwables) {
                        System.out.println("Datenbankfehler! " + throwables.getMessage() + " SQL State: "+throwables.getSQLState());
                    } catch (ClassNotFoundException e) {
                        System.out.println("Datenbankfehler! " + e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        TeacherCli teacherCli = new TeacherCli(new MySQLTeacherRepository());
                        teacherCli.start();
                    } catch (SQLException throwables) {
                        System.out.println("Datenbankfehler! " + throwables.getMessage() + " SQL State: "+throwables.getSQLState());
                    } catch (ClassNotFoundException e) {
                        System.out.println("Datenbankfehler! " + e.getMessage());
                    }
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void showMenue() {
        System.out.println("---- Hauptmenü -----");
        System.out.println("(1) Stundentenmenü \t (2) Lehrermenü");
        System.out.println("(x) ENDE");
    }



    private void inputError() {
        System.out.println("Bitte nur gültige Werte eingeben!");
    }

}
