package dataaccess;

import domain.Teacher;

import java.util.List;

public interface MyTeacherRepository extends BaseRepository<Teacher, Long> {
    List<Teacher> findByLastName(String name);
    List<Teacher> findByFirstName(String name);
    List<Teacher> findByCourse(String course);
}
