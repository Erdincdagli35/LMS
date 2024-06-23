package com.edsoft.lms.validation;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryValidation {

    @Autowired
    LibraryRepository libraryRepository;

    public boolean existsLibrary(Library library) {
        return libraryRepository.findOneById(library.getId()) == null;
    }

    public boolean notExistsLibrary(Library library) {
        return libraryRepository.findOneById(library.getId()) != null;
    }

    public boolean existsLibraryById(Long id) {
        return libraryRepository.findOneById(id) != null;
    }

    public boolean existsLibraryRelatedShelf(Long libraryId, Long[] shelfIds) {
        Library library = new Library();
        for (Long shelfId : shelfIds) library = libraryRepository.findOneByIdAndShelves_Id(libraryId, shelfId);
        return library != null ? true : false;
    }

    public boolean existsLibraryAnyRelatedShelf(Long libraryId) {
        return libraryRepository.findOneById(libraryId).getShelves() != null;
    }

    public boolean libraryCapacityCheckOut(Long libraryId, Shelf shelf) {
        int totalStorage = 0;
        for (Shelf relatedShelf : libraryRepository.findOneById(libraryId).getShelves())
            totalStorage += relatedShelf.getStorage();

        totalStorage += shelf.getStorage();
        return libraryRepository.findOneById(libraryId).getCapacity() >= totalStorage;
    }

    public boolean notEnterZeroForStorage(Shelf shelf) {
        return shelf.getStorage() != 0;
    }
}