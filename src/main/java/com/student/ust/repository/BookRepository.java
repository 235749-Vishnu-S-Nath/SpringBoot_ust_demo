package com.student.ust.repository;

import com.student.ust.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Book repository.
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
