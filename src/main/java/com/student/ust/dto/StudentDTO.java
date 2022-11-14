package com.student.ust.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
