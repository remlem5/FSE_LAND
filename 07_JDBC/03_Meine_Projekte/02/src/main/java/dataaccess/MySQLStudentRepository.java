package dataaccess;

import domain.Student;
import exceptions.DatabaseException;
import util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLStudentRepository implements MyStundentRepository{

    private Connection con;

    public MySQLStudentRepository() throws SQLException, ClassNotFoundException {
        this.con = MySQLDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/jdbc_meins01", "root", "");
    }

    @Override
    public Optional<Student> insert(Student entity) {
        Assert.notNull(entity);
        try {
            String sql = "INSERT INTO `students` (`firstName`, `lastName`, `dateOfBirth`) VALUES(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setDate(3, entity.getDateOfBirth());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0){
                return Optional.empty();
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                return this.getById(generatedKeys.getLong(1));
            } else {
                return Optional.empty();
            }

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Student> getById(Long id) {
        Assert.notNull(id);

        if(countStudentsInDbById(id) == 0){
            return Optional.empty();
        } else {
            try{
                String sql = "SELECT * FROM `students` WHERE `id`=?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth")
                );
                return Optional.of(student);
            } catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }
    }

    private int countStudentsInDbById(Long id) {
        try {
            String countSql = "SELECT COUNT(*) FROM `students` WHERE `id`=?";
            PreparedStatement preparedStatement = con.prepareStatement(countSql);
            preparedStatement.setLong(1, id);
            ResultSet resultSetCount = preparedStatement.executeQuery();
            resultSetCount.next();
            return resultSetCount.getInt(1);
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM `students`";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();
            while (resultSet.next()){
                studentList.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth")
                ));
            }
            return studentList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Student> update(Student entity) {
        Assert.notNull(entity);

        String sql = "UPDATE `students` SET `firstName`=?, `lastName`=?, `dateOfBirth`=? WHERE `students`.`id`=?";

        if (countStudentsInDbById(entity.getId()) == 0){
            return Optional.empty();
        } else {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                preparedStatement.setDate(3, entity.getDateOfBirth());
                preparedStatement.setLong(4, entity.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0){
                    return Optional.empty();
                } else {
                    return this.getById(entity.getId());
                }

            } catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }

    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id);

        String sql = "DELETE FROM `students` WHERE `id` = ?";

        try {
            if (countStudentsInDbById(id) == 1){
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Student> findByLastName(String name) {
        try {
            String sql = "SELECT * FROM `students` WHERE LOWER(`lastName`) LIKE LOWER (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();

            while (resultSet.next()){
                studentList.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth")
                ));
            }
            return studentList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Student> findByFirstName(String name) {
        try {
            String sql = "SELECT * FROM `students` WHERE LOWER(`firstName`) LIKE LOWER (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();

            while (resultSet.next()){
                studentList.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateOfBirth")
                ));
            }
            return studentList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }
}
