package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void updateStudent(Student student){
        Student updateStudent = studentRepository.findById(student.getId()).orElseThrow(()->new NoSuchElementException());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        studentRepository.save(updateStudent);
    }

    public void removeStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
