package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Course;
import com.itkolleg.Aufgabe4.entity.Student;
import com.itkolleg.Aufgabe4.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courseList = courseRepository.findAll();
        System.out.println(courseList);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Dominik")
                .lastName("Moser")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(78)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

        System.out.println(totalPages);
        System.out.println(totalElements);
        System.out.println(courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0,2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0,2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println(courses);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByTitleContaining("i", firstPageTenRecords).getContent();

        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Melanie")
                .lastName("Osl")
                .build();

        Student student = Student.builder()
                .firstName("Josef")
                .lastName("Reiter")
                .emailId("josereiter@tsn.at")
                .build();

        Course course = Course.builder()
                .title("POS")
                .credit(25)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }

}