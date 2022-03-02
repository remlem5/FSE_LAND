package dataaccess;

import domain.Student;

import java.sql.Date;
import java.util.List;

public interface MyStudentRepository extends BaseRepository<Student, Long>{
    List<Student> findByLastName(String name);
    List<Student> findByFirstName(String name);
}
