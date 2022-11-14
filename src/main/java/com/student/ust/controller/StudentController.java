package com.student.ust.controller;

import com.student.ust.dto.StudentDTO;
import com.student.ust.entity.Student;
import com.student.ust.exception.BusinessException;
import com.student.ust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * The type Student controller.
 */
@RestController
public class StudentController {
    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable Integer id){
        try{
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<StudentDTO>(studentService.convertToDto(student), HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
        }
    }

     /**
     * Gets request.
     *
     * @param studentId the student id
     * @return the request
     */
    @GetMapping("/students")
    public ResponseEntity<StudentDTO> getRequest(@RequestParam(name = "id") Integer studentId){
        try{
            Student student = studentService.getStudentById(studentId);
            return new ResponseEntity<StudentDTO>(studentService.convertToDto(student), HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>> getAll(){
        try{
            List<Student> studentList = studentService.getAllStudents();
            return new ResponseEntity<List<StudentDTO>>(studentService.convertToDtoList(studentList), HttpStatus.OK);
        }
        catch(NoSuchElementException e) {
            return new ResponseEntity<List<StudentDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Remove.
     *
     * @param id the id
     */
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> remove(@PathVariable Integer id){
        try {
            studentService.removeStudent(id);
            return new ResponseEntity<Student>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/student")
    public ResponseEntity<Student> add(@RequestBody Student student){
        try {
            studentService.saveStudent(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch(BusinessException e){
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
