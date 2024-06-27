package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.LibraryRepository;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.LibraryService;
import com.edsoft.lms.service.ShelfService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class LibraryServiceImp implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final ShelfRepository shelfRepository;
    private final ShelfService shelfService;

    @Override
    public Long create(Library library) {
        library.setCurrentCapacity(library.getCapacity());
        return libraryRepository.save(library).getId();
    }

    public void setCurrentCapacityWhenSavedTheShelf(Library library) {
        Integer totalShelfStorage = 0;
        List<Shelf> shelfList = library.getShelves();
        for (Shelf shelf : shelfList)
            totalShelfStorage += shelf.getStorage();

        library.setCurrentCapacity(library.getCapacity() - totalShelfStorage);
        libraryRepository.save(library);
    }

    @Override
    public List<Library> getAll(String name) {
        List<Library> libraries = (name == null) ? libraryRepository.findAllByOrderById() : libraryRepository.findAllByName(name);
        for (Library library : libraries) setCurrentCapacityWhenSavedTheShelf(library);
        return libraries;
    }

    @Override
    public Library edit(Library libraryTemp, Library library) {
        libraryTemp.setName(library.getName());
        libraryTemp.setCapacity(library.getCapacity());
        libraryTemp.setCurrentCapacity(library.getCapacity());

        return libraryRepository.save(libraryTemp);
    }

    @Override
    public Long delete(Library library) {
        libraryRepository.delete(library);
        return library.getId();
    }

    @Override
    public Library getById(Long libraryId) {
        return libraryRepository.findOneById(libraryId);
    }

    @Override
    public Long addToShelf(Shelf shelf, Long libraryId) {
        Library library = getById(libraryId);
        shelf.setLibrary(library);
        //library.getShelves().addAll(shelf);
        shelfRepository.save(shelf);
        libraryRepository.save(library);
        return shelf.getId(); //shelves.stream().map(Shelf::getId).toList();
    }

    @Override
    public List<Long> removeAllShelf(Long libraryId) {
        Library library = getById(libraryId);

        List<Long> shelveIds = new ArrayList<>();
        for (Shelf shelf : getById(libraryId).getShelves()) shelveIds.add(shelf.getId());

        for (Shelf shelf : library.getShelves()) {
            shelf.setLibrary(null);
            shelfRepository.save(shelf);
        }
        library.setShelves(null);
        library.setCurrentCapacity(library.getCapacity());
        libraryRepository.save(library);

        return shelveIds;
    }
}
