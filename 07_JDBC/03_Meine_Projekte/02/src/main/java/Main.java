import dataaccess.MySQLStudentRepository;
import dataaccess.MySQLTeacherRepository;
import ui.Cli;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Cli mycli = new Cli();
        mycli.start();
    }
}

