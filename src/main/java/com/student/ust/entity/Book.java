package com.student.ust.entity;

import lombok.Data;
import lombok.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "book_ustBatch")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
