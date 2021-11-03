package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Course;
import com.itkolleg.Aufgabe4.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course courseJava = Course.builder()
                .title("Java")
                .credit(17)
                .build();

        Course courseSpringBoot = Course.builder()
                .title("SpringBoot")
                .credit(1270)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Claudio")
                .lastName("Landerer")
                //.courses(List.of(courseJava, courseSpringBoot))
                .build();

        teacherRepository.save(teacher);
    }

}