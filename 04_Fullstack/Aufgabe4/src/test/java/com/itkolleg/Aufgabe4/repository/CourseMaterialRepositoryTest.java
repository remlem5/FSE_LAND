package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Course;
import com.itkolleg.Aufgabe4.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                .title("php")
                .credit(12)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.w3schools.at")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterialList = repository.findAll();
        System.out.println(courseMaterialList);
    }

}