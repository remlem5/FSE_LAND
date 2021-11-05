package com.example.meins.meins1_4.repos;

import com.example.meins.meins1_4.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepRepo extends JpaRepository<Department, Long> {

    Department findDepartmentByDepId(long depId);

    Department findDepartmentByDepName(String name);

}
