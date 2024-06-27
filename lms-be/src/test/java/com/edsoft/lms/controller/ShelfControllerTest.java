package com.edsoft.lms.controller;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.ShelfService;
import com.edsoft.lms.validation.BookValidation;
import com.edsoft.lms.validation.ShelfValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShelfControllerTest {

    @InjectMocks
    private ShelfController shelfController;

    @Mock
    private ShelfService shelfService;

    @Mock
    private ShelfRepository shelfRepository;

    @Mock
    private ShelfValidation shelfValidation;

    @Mock
    private BookValidation bookValidation;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testGetAll_ShouldReturnListOfShelves() {
        List<Shelf> shelves = new ArrayList<>();
        shelves.add(new Shelf(1L, "Shelf A",10,new Library()));
        shelves.add(new Shelf(2L, "Shelf B",10, new Library()));
        new Library(1L,"Library ",30,30,shelves);
        when(shelfService.getAll()).thenReturn(shelves);

        ResponseEntity<List<Shelf>> response = shelfController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testEdit_ShouldReturnEditedShelf() {
        Shelf existingShelf = new Shelf(1L, "Shelf A",10,new Library());
        Shelf updatedShelf = new Shelf(1L, "Updated Shelf A",10,new Library());

        when(shelfService.getById(existingShelf.getId())).thenReturn(existingShelf);
        when(shelfValidation.validateStorageByCapacity(existingShelf, updatedShelf)).thenReturn(true);
        when(shelfService.edit(existingShelf, updatedShelf)).thenReturn(updatedShelf);

        ResponseEntity response = shelfController.edit(updatedShelf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedShelf, response.getBody());
    }

    @Test
    public void testEdit_ShouldReturnInternalServerErrorWhenShelfNotFound() {
        Shelf updatedShelf = new Shelf(1L, "Updated Shelf A",3,new Library());

        when(shelfService.getById(updatedShelf.getId())).thenReturn(null);

        ResponseEntity response = shelfController.edit(updatedShelf);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("There is not a shelf id : " + updatedShelf.getId(), response.getBody());
    }

    @Test
    public void testDelete_ShouldReturnDeletedShelf() {
        Long shelfId = 1L;
        Shelf shelfToDelete = new Shelf(shelfId, "Shelf A",3, new Library());

        when(shelfRepository.findOneById(shelfId)).thenReturn(shelfToDelete);
        doNothing().when(shelfService).delete(shelfToDelete);

        ResponseEntity response = shelfController.delete(shelfId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelfToDelete, response.getBody());
    }


    @Test
    public void testDelete_ShouldReturnInternalServerErrorWhenShelfNotFound() {
        Long shelfId = 1L;

        when(shelfRepository.findOneById(shelfId)).thenReturn(null);

        ResponseEntity response = shelfController.delete(shelfId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("There is not a shelf id : " + shelfId, response.getBody());
    }

    @Test
    public void testGetById_ShouldReturnShelfById() {
        Long shelfId = 1L;
       Shelf shelf = new Shelf(shelfId, "Shelf A",3, new Library());

        when(shelfRepository.findOneById(shelfId)).thenReturn(shelf);
        when(shelfService.getById(shelfId)).thenReturn(shelf);

        ResponseEntity response = shelfController.getById(shelfId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shelf, response.getBody());
    }

    @Test
    public void testGetById_ShouldReturnInternalServerErrorWhenShelfNotFound() {
        Long shelfId = 1L;

        when(shelfRepository.findOneById(shelfId)).thenReturn(null);

        ResponseEntity response = shelfController.getById(shelfId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("There is not a shelf id : " + shelfId, response.getBody());
    }

    @Test
    public void testAddToBook_ShouldReturnAddedBookToShelf() throws Exception {
        Long shelfId = 1L;
        Book book = new Book(1L, "Book A", 10,"John Doe");

        when(bookValidation.existsShelfById(shelfId)).thenReturn(true);
        when(shelfService.addToBook(eq(book), eq(shelfId))).thenReturn(book.getId());

        ResponseEntity response = shelfController.addToBook(shelfId, book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(objectMapper.writeValueAsString(book), objectMapper.writeValueAsString(response.getBody()));
    }

    @Test
    public void testAddToBook_ShouldReturnInternalServerErrorWhenShelfNotFound() {
        Long shelfId = 1L;
        Book book = new Book(1L, "Book A", 10,"John Doe");

        when(bookValidation.existsShelfById(shelfId)).thenReturn(false);

        ResponseEntity response = shelfController.addToBook(shelfId, book);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("There is not a Shelf id : " + shelfId, response.getBody());
    }*/
}
