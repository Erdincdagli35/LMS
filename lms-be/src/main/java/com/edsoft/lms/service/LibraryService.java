package com.edsoft.lms.service;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;

import java.util.List;

public interface LibraryService {
    Long create(Library library);

    List<Library> getAll(String name);

    Library edit(Library libraryTemp, Library library);

    Long delete(Library library);

    Library getById(Long libraryId);

    List<Long> addToShelf(List<Shelf> shelves, Long libraryId);

    List<Long> removeToShelf(Long libraryId, Long[] shelfIds);

    List<Long> removeAllShelf(Long libraryId);

    void duplicate();
}
