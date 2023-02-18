package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Category;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class LibraryServiceImp implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final ShelfRepository shelfRepository;
    private final ShelfService shelfService;

    @Override
    public Long create(Library library) {
        return libraryRepository.save(library).getId();
    }

    @Override
    public List<Library> getAll(String name) {
        return name == null ? libraryRepository.findAllByOrderById() : libraryRepository.findAllByName(name);
    }

    @Override
    public Library edit(Library libraryTemp, Library library) {
        libraryTemp.setName(library.getName());
        libraryTemp.setCapacity(library.getCapacity());

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
    public List<Long> removeToShelf(Long libraryId, Long[] shelfIds) {
        Library library = getById(libraryId);

        for (Long shelfId : shelfIds) {
            shelfService.getById(shelfId).setLibrary(null);
            shelfRepository.save(shelfService.getById(shelfId));
        }

        libraryRepository.save(library);
        return Arrays.stream(shelfIds).toList();
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
        libraryRepository.save(library);

        return shelveIds;
    }


    @Override
    public void duplicate() {
        /*Book book = new Book();
        book.setId(1L);
        book.setAuthor("Erdinç");
        book.setName("Book");
        book.setCategory(Category.Action);
        List<Category> categories = new ArrayList<>();
        categories.add(Category.Action);
        categories.add(Category.History);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setAuthor("Erdinç");
        book2.setName("Book");
        book2.setCategory(Category.Horror);


        if (categories.contains(book.getCategory())){
            System.out.println("True");
        }
        if (categories.contains(book2.getCategory())) {
            System.out.println("True");
        }else{
            System.out.println("False");
        }*/



       /* List<Library> libraryList = new ArrayList<>();
        Library library = new Library(1L, "City", 12, null);
        Library library2 = new Library(1L, "City", 13, null);

        libraryList.add(library);
        libraryList.add(library2);

        for (int i = 0; i < libraryList.size(); i++) {
            for (int j = 0; j < libraryList.size(); j++) {
                if (i!=j &&
                            libraryList.get(i).equals(libraryList.get(j))) {
                    System.out.println("Suplicate");
                }
            }
        }*/
        /*
        String ip = "10.254.135.84";
        String[] INET4ADDRESS = new String[]{"0.1.2.3", "1.2.3.4", "10.20.49.91", "10.20.49.93", "10.53.37.91", "10.53.37.93", "10.254.135.84"};
        // Validate an IPv4 address
        for (int i = 0; i < INET4ADDRESS.length; i++) {
            if (isValidInet4Address(INET4ADDRESS[i])) {
                System.out.println("The IP address " + INET4ADDRESS[i] + " is valid");
            } else {
                System.out.println("The IP address " + INET4ADDRESS[i] + " isn't valid");
            }
        }

        String[] strArr = ip.split("\\.");
        long address = Long.parseLong(strArr[0]);

        for (int i = 1; i < 4; i++) {
            address = (address << 8) + Long.parseLong(strArr[i]);
        }

        System.out.println("Address : " + address);*/
    }
}
