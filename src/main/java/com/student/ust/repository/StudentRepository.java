package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByName(String name);
    Student findByNameStartingWith(String pattern);

    @Query("Select s From Student s WHERE s.age=?1")
    Student findStudentByAge(int age);
}
