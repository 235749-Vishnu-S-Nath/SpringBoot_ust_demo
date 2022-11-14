package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book service.
 */
@Service
public class BookService {

    /**
     * The Book repository.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Save book.
     *
     * @param book the book
     */
    public void saveBook(Book book) {
        book.setBookCreatedDate(LocalDateTime.now());
        book.setBookModifiedDate(book.getBookCreatedDate());
        bookRepository.save(book);
    }

    /**
     * Gets book.
     *
     * @param id the id
     * @return the book
     */
    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Gets all books.
     *
     * @return the all books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Remove book.
     *
     * @param id the id
     */
    public void removeBook(Integer id) {
        bookRepository.deleteById(id);
    }

    /**
     * Update book book.
     *
     * @param book the book
     * @return the book
     */
    public Book updateBook(Book book) {
        Book updatedBook = bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updatedBook.setBookAuthorName(book.getBookAuthorName());
        updatedBook.setBookName(book.getBookName());
        updatedBook.setBookIsbNo(book.getBookIsbNo());
        updatedBook.setBookModifiedDate(LocalDateTime.now());
        bookRepository.save(updatedBook);
        return updatedBook;
    }
}
