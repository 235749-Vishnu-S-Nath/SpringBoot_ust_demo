package com.student.ust.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Book.
 */
@Entity
@Data
@Table(name = "book_ustBatch_table")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int bookId;
    private String bookName;
    private String bookAuthorName;
    private int bookIsbNo;
    private LocalDateTime bookCreatedDate;
    private LocalDateTime bookModifiedDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
