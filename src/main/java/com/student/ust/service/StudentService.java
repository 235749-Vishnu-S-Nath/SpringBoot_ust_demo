package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void saveStudent(Student student) {
        student.setCreatedDate(LocalDateTime.now());
        student.setModifiedDate(student.getCreatedDate());
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student){
        Student updatedStudent = studentRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
        updatedStudent.setRollNo(student.getRollNo());
        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setModifiedDate(LocalDateTime.now());
        studentRepository.save(updatedStudent);
        return updatedStudent;
    }

    public void removeStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
