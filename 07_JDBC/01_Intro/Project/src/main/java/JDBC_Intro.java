import java.sql.*;

public class JDBC_Intro {

    public static void main(String[] args){
        System.out.println("JDBC_intro");
        selectAllDemo();
        findAllByNameLike("m");
    }

    public static void findAllByNameLike(String needle){
        System.out.println("Find All By Name");
        String sql = "SELECT * FROM `student` WHERE `student`.`name` LIKE ?";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbc_intro";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%"+needle+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id"); //oder ColumnIndex 1
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student: ID: " +id +" NAME: "+name+" E-MAIL: "+email);
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: "+e.getMessage());
        }
    }

    public static void deleteStudentDemo(int studentId){
        System.out.println("Delete");
        String sql = "DELETE FROM `student` WHERE `student`.`id` = ?";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbc_intro";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try {
                preparedStatement.setInt(1, studentId);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: "+rowAffected);
            } catch (SQLException ex){
                System.out.println("Fehler im SQL-DELETE-Statement: "+ex.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: "+e.getMessage());
        }
    }

    public static void updateStudentDemo(int studentId, String neuerName, String neueEmail){
        System.out.println("Update");
        String sql = "UPDATE `student` SET `name` = ?, `email` = ? WHERE `student`.`id` = ?";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbc_intro";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try {
                preparedStatement.setString(1, neuerName);
                preparedStatement.setString(2, neueEmail);
                preparedStatement.setInt(3, studentId);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: "+rowsAffected);
            } catch (SQLException ex){
                System.out.println("Fehler im SQL-UPDATE-Statement: "+ex.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: "+e.getMessage());
        }
    }

    public static void insertStudentDemo(String name, String email){
        System.out.println("Insert into");
        String sql = "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbc_intro";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            try {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze eingefügt");
            } catch (SQLException ex){
                System.out.println("Fehler im SQL-INSERT-Statement: "+ex.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: "+e.getMessage());
        }
    }

    public static void selectAllDemo(){
        System.out.println("Select All");
        String sql = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbc_intro";
        String user = "root";
        String pwd = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id"); //oder ColumnIndex 1
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student: ID: " +id +" NAME: "+name+" E-MAIL: "+email);
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: "+e.getMessage());
        }
    }

}
