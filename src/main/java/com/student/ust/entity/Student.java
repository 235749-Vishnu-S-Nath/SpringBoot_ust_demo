package com.student.ust.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * The type Student.
 */
@Entity
@Data
@Table(name="student_ustBatch_table_demo")
public class Student {
    /**
     * Instantiates a new Student.
     */
    public Student(){}
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @NonNull
    private String email;
    @NonNull
    private String password;
/**
*    @OneToOne
*    @JoinColumn(name = "book_id")
*    private Book book;
*/

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private Set<Book> bookList;

    @ManyToMany
    @JoinColumn(name="tutor_id")
    private List<Tutor> tutor;

}
