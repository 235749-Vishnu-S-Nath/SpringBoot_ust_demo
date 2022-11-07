package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Tutor.
 */
@Entity
@Data
@Table(name = "tutor_ustBatch")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tutorId;
    private String name;
    private String subject;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @ManyToMany
    @JoinColumn(name = "student_id")
    private List<Student> student;

}
