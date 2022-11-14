package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;
import com.student.ust.repository.StudentRepository;
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

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(Integer id) {
        Student studentById=studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        //getStudentByName(studentById.getName());
        //Student student = studentRepository.findByNameStartingWith("Ki");
        //System.out.println(student.getName());
        //System.out.println(studentRepository.findStudentByAge(23));
        return studentById;

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

    public void getStudentByName(String name) {
        Student studentByName=studentRepository.findByName(name);
        System.out.println("Student name is ......"+ studentByName.getName());
        System.out.println("Student age is ......"+ studentByName.getAge());
    }

    public int validateEmail(String email){
        String regex="^([A-Za-z0-9+_.-]+)@([A-Za-z0-9]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return 0;
        }
        else{
            throw new InvalidEmail();
        }
    }

    public int validatePassword(String password) {
        String regex = "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,}).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return 0;
        }
        else {
            throw new InvalidPassword();
        }
    }

    public String hashPassword(String password) {
        try {
            return toHexString(getSHA(password));
        }
        catch(NoSuchAlgorithmException e){
            return null;
        }
    }

    private byte[] getSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
