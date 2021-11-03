package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
