package com.itkolleg.Aufgabe4.repository;

import com.itkolleg.Aufgabe4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String name);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    //im Video Student statt tbl_student. funktioniert bei mir nicht...
    @Query("select s from tbl_student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("select s.firstName from tbl_student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native Query
    @Query(value = "select * from tbl_student s where s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    //Native Query
    @Query(value = "select * from tbl_student s where s.email_address = :emailId", nativeQuery = true)
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name = ?1 where email_address = ?2", nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);




}
