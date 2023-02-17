package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.repository.BookRepository;
import com.edsoft.lms.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll(String name) {
        return bookRepository.findAll();
    }

    @Override
    public Book edit(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Long delete(Book book) {
        bookRepository.delete(book);
        return book.getId();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findOneById(id);
    }
}
