package com.edsoft.lms.controller;

import com.edsoft.lms.model.Book;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.ShelfService;
import com.edsoft.lms.validation.BookValidation;
import com.edsoft.lms.validation.ShelfValidation;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * I created the Controller for Shelf
 * APIs :
 *   create POST
 *   getAll GET
 *   del DELETE
 *   getById GET
 *   edit   PUT
 * */
@RestController
@RequestMapping("/shelves")
@EnableAutoConfiguration
@CrossOrigin
@AllArgsConstructor
public class ShelfController {

    private final ShelfService shelfService;
    private final ShelfRepository shelfRepository;

    private final ShelfValidation shelfValidation;
    private final BookValidation bookValidation;


    @GetMapping
    public ResponseEntity<List<Shelf>> getAll() {
        return ResponseEntity.ok(shelfService.getAll());
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Shelf shelf) {
        Shelf shelfTemp = shelfService.getById(shelf.getId());
        if (shelfTemp == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a shelf id : " + shelf.getId());
        }
        if (!shelfValidation.validateStorageByCapacity(shelfTemp,shelf)){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a shelf id : " + shelf.getId());
        }

        return ResponseEntity.ok(shelfService.edit(shelfTemp, shelf));
    }

    @DeleteMapping("/{shelfId}")
    public ResponseEntity delete(@PathVariable Long shelfId) {
        Shelf shelf = shelfRepository.findOneById(shelfId);
        if (shelf == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a shelf id : " + shelfId);
        }

        return ResponseEntity.ok(shelfService.delete(shelf));
    }

    @GetMapping("/{shelfId}")
    public ResponseEntity getById(@PathVariable Long shelfId) {
        Shelf shelf = shelfRepository.findOneById(shelfId);
        if (shelf == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a shelf id : " + shelfId);
        }

        return ResponseEntity.ok(shelfService.getById(shelf.getId()));
    }

    @PostMapping("addBook/{shelfId}")
    public ResponseEntity addToBook(@PathVariable Long shelfId,
                                     @RequestBody Book book) {
        if (!bookValidation.existsShelfById(shelfId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Shelf id : " + shelfId);
        }

        return ResponseEntity.ok(shelfService.addToBook(book, shelfId));
    }
}
