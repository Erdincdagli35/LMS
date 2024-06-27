package com.edsoft.lms.service;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Shelf;

import java.util.List;
import java.util.Set;

public interface ShelfService {
    List<Shelf> getAll();

    Shelf edit(Shelf shelfTemp,Shelf shelf);

    Long delete(Shelf shelf);

    Shelf getById(Long id);

    List<Shelf> getByIds(Long[] id);

    Long addToBook(Book book, Long shelfId);
}
