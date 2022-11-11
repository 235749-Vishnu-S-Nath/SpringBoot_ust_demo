package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id){
        log.debug("Id value in get method "+id);
        try{
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Student> getRequest(@RequestParam(name = "id") Integer studentId){
        try{
            Student student = studentService.getStudentById(studentId);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAll(){
        try{
            List<Student> studentList = studentService.getAllStudents();
            return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")
    public void remove(@PathVariable Integer id){
        studentService.removeStudent(id);
    }

    @PostMapping("/student")
    public void add(@RequestBody Student student){
        log.debug("Student details >>>"+student.getStudentId()+" "+student.getName());
        studentService.saveStudent(student);
    }
}
