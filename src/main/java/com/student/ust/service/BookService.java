package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(Book book) {
        book.setBookCreatedDate(LocalDateTime.now());
        book.setBookModifiedDate(book.getBookCreatedDate());
        bookRepository.save(book);
    }

    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void removeBook(Integer id) {
        bookRepository.deleteById(id);
    }

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
