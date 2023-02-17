package com.edsoft.lms.controller;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.repository.BookRepository;
import com.edsoft.lms.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * I created the Controller for Book
 * APIs :
 *   create POST
 *   getAll GET
 *   del DELETE
 *   getById GET
 *   edit   PUT
 * */
@RestController
@RequestMapping("/books")
@EnableAutoConfiguration
@CrossOrigin
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(bookService.getAll(name));
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Book book) {
        Book bookTemp = bookRepository.findOneById(book.getId());
        if (bookTemp == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a book id : " + book.getId());
        }
        return ResponseEntity.ok(bookService.edit(book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity delete(@PathVariable Long bookId) {
        Book book = bookRepository.findOneById(bookId);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a book id : " + bookId);
        }

        return ResponseEntity.ok(bookService.delete(book));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity getById(@PathVariable Long bookId) {
        Book book = bookRepository.findOneById(bookId);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a book id : " + bookId);
        }

        return ResponseEntity.ok(bookService.getById(book.getId()));
    }
}
