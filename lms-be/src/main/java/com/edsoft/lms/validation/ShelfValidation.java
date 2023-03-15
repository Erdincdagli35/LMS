package com.edsoft.lms.validation;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShelfValidation {

    @Autowired
    ShelfRepository shelfRepository;

    public boolean existsShelfById(Long[] shelfIds) {
        List<Shelf> shelves = new ArrayList<>();
        for (Long shelfId : shelfIds) {
            Shelf shelf = shelfRepository.findOneById(shelfId);
            shelves.add(shelf);
        }

        return shelves != null?true : false;
    }

    public boolean validateStorageByCapacity(Shelf shelfOld, Shelf shelf) {
        Library library = shelfOld.getLibrary();
        Integer newCurrentCapacity = library.getCurrentCapacity() + shelfOld.getStorage();
        return shelf.getStorage() <= newCurrentCapacity;
    }
}
