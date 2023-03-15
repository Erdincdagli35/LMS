package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.BookRepository;
import com.edsoft.lms.repository.LibraryRepository;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.ShelfService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
@AllArgsConstructor
public class ShelfServiceImp implements ShelfService {
    private final BookRepository bookRepository;
    private final ShelfRepository shelfRepository;
    private final LibraryRepository libraryRepository;

    @Override
    public List<Shelf> getAll() {
        List<Shelf> shelves = shelfRepository.findAllByOrderByIdDesc();
        List<Shelf> relatedShelves = new ArrayList<>();

        for (Shelf shelf : shelves){
            if (shelf.getLibrary() != null){
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
    public Long addToBook(Book book, Long shelfId) {
        /*Shelf shelf = getById(shelfId);

        Set<Book> books = new HashSet<>();
        books.add(book);
        shelf.setBooks(books);

        bookRepository.save(book);
        shelfRepository.save(shelf);*/
        return null;
    }
}
