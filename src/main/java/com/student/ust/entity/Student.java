package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
}
