package com.edsoft.lms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ModelTest {

    @Test
    public void testBookGettersAndSetters() {
        Book book = new Book();
        book.setName("Test Book");
        book.setPageNumber(100);
        book.setAuthor("Test Author");

        assertEquals("Test Book", book.getName());
        assertEquals(100, book.getPageNumber());
        assertEquals("Test Author", book.getAuthor());
    }

    @Test
    public void testLibraryGettersAndSetters() {
        Library library = new Library();
        library.setName("Test Library");
        library.setCapacity(500);
        library.setCurrentCapacity(100);
        library.setShelves(new ArrayList<>());

        assertEquals("Test Library", library.getName());
        assertEquals(500, library.getCapacity());
        assertEquals(100, library.getCurrentCapacity());
        assertTrue(library.getShelves().isEmpty());
    }

    @Test
    public void testLibraryEqualsAndHashCode() {
        Library library1 = new Library();
        library1.setName("Test Library");

        Library library2 = new Library();
        library2.setName("Test Library");

        assertTrue(library1.equals(library2));
        assertEquals(library1.hashCode(), library2.hashCode());
    }

    @Test
    public void testShelfGettersAndSetters() {
        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");
        shelf.setStorage(50);

        assertEquals("Test Shelf", shelf.getName());
        assertEquals(50, shelf.getStorage());
    }

    @Test
    public void testLibraryAndShelfRelationship() {
        Library library = new Library();
        library.setName("Test Library");
        library.setCapacity(500);
        library.setCurrentCapacity(100);

        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");
        shelf.setStorage(50);
        shelf.setLibrary(library);

        List<Shelf> shelves = new ArrayList<>();
        shelves.add(shelf);
        library.setShelves(shelves);

        assertEquals(1, library.getShelves().size());
        assertEquals("Test Shelf", library.getShelves().get(0).getName());
        assertEquals(library, library.getShelves().get(0).getLibrary());
    }

    @Test
    public void testShelfLibraryRelationship() {
        Library library = new Library();
        library.setName("Test Library");
        library.setCapacity(500);
        library.setCurrentCapacity(100);

        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");
        shelf.setStorage(50);

        shelf.setLibrary(library);

        assertEquals("Test Library", shelf.getLibrary().getName());
        assertEquals(500, shelf.getLibrary().getCapacity());
    }
}
