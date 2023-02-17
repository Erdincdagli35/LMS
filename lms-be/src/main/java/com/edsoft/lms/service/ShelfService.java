package com.edsoft.lms.service;

import com.edsoft.lms.model.Shelf;

import java.util.List;

public interface ShelfService {
    List<Shelf> getAll(String name);

    Shelf edit(Shelf shelf);

    Long delete(Shelf shelf);

    Shelf getById(Long id);

    List<Shelf> getByIds(Long[] id);
}
