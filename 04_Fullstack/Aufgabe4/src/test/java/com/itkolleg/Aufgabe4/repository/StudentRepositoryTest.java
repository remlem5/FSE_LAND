package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Guardian;
import com.itkolleg.Aufgabe4.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("tmelmer@tsn.at")
                .firstName("Tobias")
                .lastName("Melmer")
                //.guardianName("Landerer")
                //.guardianEmail("clanderer@tsn.at")
                //.guardianMobile("06761234567")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("clanderer@tsn.at")
                .name("Landerer")
                .mobile("06767654321")
                .build();

        Student student = Student.builder()
                .firstName("Thomas")
                .lastName("Hofmann")
                .emailId("thpaulweber@tsn.at")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList = "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Thomas");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("as");
        System.out.println(students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Landerer");
        System.out.println(students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("tmelmer@tsn.at");
        System.out.println(student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String name = studentRepository.getStudentFirstNameByEmailAddress("tmelmer@tsn.at");
        System.out.println(name);
    }

}