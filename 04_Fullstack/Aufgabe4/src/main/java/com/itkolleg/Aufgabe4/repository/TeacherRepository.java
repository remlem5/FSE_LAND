package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
