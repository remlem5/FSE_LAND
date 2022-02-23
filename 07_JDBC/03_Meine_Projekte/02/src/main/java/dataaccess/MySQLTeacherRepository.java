package dataaccess;

import domain.Teacher;
import exceptions.DatabaseException;
import util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLTeacherRepository implements MyTeacherRepository{

    private Connection con;

    public MySQLTeacherRepository() throws SQLException, ClassNotFoundException{
        this.con = MySQLDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/jdbc_meins01", "root", "");
    }

    @Override
    public Optional<Teacher> insert(Teacher entity) {
        Assert.notNull(entity);
        try{
            String sql = "INSERT INTO `teachers` (`firstName`, `lastName`, `courses`) VALUES(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getCourses().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0){
                return Optional.empty();
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                return this.getById(generatedKeys.getLong(1));
            } else {
                return Optional.empty();
            }

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        Assert.notNull(id);

        if(countTeachersInDbById(id) == 0){
            return Optional.empty();
        } else {
            try{
                String sql = "SELECT * FROM `teachers` WHERE `id`=?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();

                Teacher teacher = new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("courses")
                );
                return Optional.of(teacher);
            } catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM `teachers`";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<>();
            while (resultSet.next()){
                teacherList.add(new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("courses"))
                );
            }
            return teacherList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Teacher> update(Teacher entity) {
        Assert.notNull(entity);

        String sql = "UPDATE `teachers` SET `firstName`=?, `lastName`=?, `courses`=? WHERE `teachers`.`id`=?";

        if (countTeachersInDbById(entity.getId()) == 0){
            return Optional.empty();
        } else {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                preparedStatement.setString(3, entity.getCourses());
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
        String sql = "DELETE FROM `teachers` WHERE `id` = ?";
        try {
            if (countTeachersInDbById(id) == 1) {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Teacher> findByLastName(String name) {
        try {
            String sql = "SELECT * FROM `teachers` WHERE LOWER(`lastName`) LIKE LOWER (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<>();

            while (resultSet.next()){
                teacherList.add(new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("courses"))
                );
            }
            return teacherList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Teacher> findByFirstName(String name) {
        try {
            String sql = "SELECT * FROM `teachers` WHERE LOWER(`firstName`) LIKE LOWER (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<>();

            while (resultSet.next()){
                teacherList.add(new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("courses"))
                );
            }
            return teacherList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Teacher> findByCourse(String course) {
        try {
            String sql = "SELECT * FROM `teachers` WHERE LOWER(`courses`) LIKE LOWER (?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "%"+course+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Teacher> teacherList = new ArrayList<>();

            while (resultSet.next()){
                teacherList.add(new Teacher(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("courses"))
                );
            }
            return teacherList;
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    private int countTeachersInDbById(Long id) {
        try {
            String countSql = "SELECT COUNT(*) FROM `teachers` WHERE `id`=?";
            PreparedStatement preparedStatement = con.prepareStatement(countSql);
            preparedStatement.setLong(1, id);
            ResultSet resultSetCount = preparedStatement.executeQuery();
            resultSetCount.next();
            return resultSetCount.getInt(1);
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }
}
