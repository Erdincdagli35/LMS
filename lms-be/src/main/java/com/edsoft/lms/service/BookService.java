package com.edsoft.lms.service;

import com.edsoft.lms.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll(String name);

    Book edit(Book book);

    Long delete(Book book);

    Book getById(Long id);
}
