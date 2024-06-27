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

    Long addToShelf(Shelf shelf, Long libraryId);

    List<Long> removeAllShelf(Long libraryId);
}
