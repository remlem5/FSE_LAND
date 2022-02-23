package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection {
    private static Connection con = null;

    private MySQLDatabaseConnection(){}

    public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException {
        if (con != null){
            return con;
        } else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            return con;
        }
    }
}
