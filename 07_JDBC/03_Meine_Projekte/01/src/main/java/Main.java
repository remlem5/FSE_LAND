import dataaccess.MySQLStudentRepository;
import ui.Cli;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Cli mycli = new Cli(new MySQLStudentRepository());
            mycli.start();
        } catch (SQLException throwables) {
            System.out.println("Datenbankfehler! " + throwables.getMessage() + " SQL State: "+throwables.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler! " + e.getMessage());
        }
    }
}

