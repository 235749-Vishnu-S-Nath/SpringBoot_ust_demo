package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="student_ustBatch")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
/**
*    @OneToOne
*    @JoinColumn(name = "book_id")
*    private Book book;
*/

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> bookList;

//    @ManyToMany
//    @JoinColumn(name="book_id")
//    private List<Book> book;

}
