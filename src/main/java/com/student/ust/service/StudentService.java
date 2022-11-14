package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.exception.BusinessException;
import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;
import com.student.ust.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.student.ust.utils.UstUtils.*;

/**
 * The type Student service.
 */
@Slf4j
@Service
public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    public Student getStudentById(Integer id) {
        Student studentById=studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        return studentById;

    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) throws BusinessException {
        log.debug(student.getName()+"\n");
        log.debug(student.getRollNo()+"\n");
        student.setCreatedDate(LocalDateTime.now());
        student.setModifiedDate(student.getCreatedDate());
        boolean emailResult=validateEmail(student.getEmail());
        boolean passwordResult=validatePassword(student.getPassword());
        if(emailResult && passwordResult){
            student.setPassword(hashPassword(student.getPassword()));
            studentRepository.save(student);
        }
        else{
            throw new BusinessException("Invalid Email or Password");
        }
    }

    /**
     * Gets all students.
     *
     * @return the all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student){
        Student updatedStudent = studentRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
        updatedStudent.setRollNo(student.getRollNo());
        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setModifiedDate(LocalDateTime.now());
        studentRepository.save(updatedStudent);
        return updatedStudent;
    }

    /**
     * Remove student.
     *
     * @param id the id
     */
    public void removeStudent(Integer id) {
        Student deleteStudent = studentRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        studentRepository.deleteById(id);
    }

    /**
     * Gets student by name.
     *
     * @param name the name
     */
    public void getStudentByName(String name) {
        Student studentByName=studentRepository.findByName(name);
        System.out.println("Student name is ......"+ studentByName.getName());
        System.out.println("Student age is ......"+ studentByName.getAge());
    }
}
