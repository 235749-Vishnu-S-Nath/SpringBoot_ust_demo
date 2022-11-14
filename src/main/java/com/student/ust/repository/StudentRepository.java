package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    /**
     * Find by name student.
     *
     * @param name the name
     * @return the student
     */
    Student findByName(String name);

    /**
     * Find by name starting with student.
     *
     * @param pattern the pattern
     * @return the student
     */
    Student findByNameStartingWith(String pattern);

    /**
     * Find student by age student.
     *
     * @param age the age
     * @return the student
     */
    @Query("Select s From Student s WHERE s.age=?1")
    Student findStudentByAge(int age);
}
