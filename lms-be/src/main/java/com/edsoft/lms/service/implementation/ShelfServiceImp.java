package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.BookRepository;
import com.edsoft.lms.repository.LibraryRepository;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.ShelfService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class ShelfServiceImp implements ShelfService {
    private final BookRepository bookRepository;
    private final ShelfRepository shelfRepository;
    private final LibraryRepository libraryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shelf> getAll() {
        List<Shelf> shelves = shelfRepository.findAllByOrderByIdDesc();
        List<Shelf> relatedShelves = new ArrayList<>();

        for (Shelf shelf : shelves) {
            if (shelf.getLibrary() != null) {
                relatedShelves.add(shelf);
            }
        }

        return relatedShelves;
    }

    @Override
    public Shelf edit(Shelf shelfTemp, Shelf shelf) {
        shelfTemp.setName(shelf.getName());
        shelfTemp.setStorage(shelf.getStorage());

        return shelfRepository.save(shelfTemp);
    }

    @Override
    public Long delete(Shelf shelf) {
        Library library = shelf.getLibrary();
        library.setCurrentCapacity(library.getCapacity() - shelf.getStorage());
        libraryRepository.save(library);
        shelf.setLibrary(null);
        shelfRepository.delete(shelf);
        return shelf.getId();
    }

    @Override
    public Shelf getById(Long id) {
        return shelfRepository.findOneById(id);
    }

    @Override
    public List<Shelf> getByIds(Long[] ids) {
        List<Shelf> shelves = new ArrayList<>();
        for (Long id : ids) shelves.add(getById(id));
        return shelves;
    }

    @Override
    @Transactional
    public Long addToBook(Book book, Long shelfId) {
        // Fetch the shelf entity and ensure it is managed
        Shelf shelf = entityManager.find(Shelf.class, shelfId);

        if (shelf == null) {
            throw new EntityNotFoundException("Shelf not found");
        }

        // Add the book to the shelf
        shelf.addBook(book);

        // Merge the shelf to ensure it's managed and the book is cascaded
        entityManager.merge(shelf);

        if (shelf.getStorage() != 0) {
            shelf.setStorage(shelf.getStorage() - 1);
        }

        // No need to explicitly save the book, as it will be cascaded by the shelf
        return book.getId();
    }

}
