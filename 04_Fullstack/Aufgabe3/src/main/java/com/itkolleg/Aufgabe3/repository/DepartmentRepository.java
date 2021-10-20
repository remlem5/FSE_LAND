package com.itkolleg.Aufgabe3.repository;

import com.itkolleg.Aufgabe3.entitiy.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByDepartmentName(String depName);

    public Department findByDepartmentNameIgnoreCase(String depName);
}
