package com.edsoft.lms.controller;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.LibraryRepository;
import com.edsoft.lms.service.LibraryService;
import com.edsoft.lms.validation.LibraryValidation;
import com.edsoft.lms.validation.ShelfValidation;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
 * I created the Controller for Library
 * APIs :
 *   create POST
 *   getAll GET
 *   del DELETE
 *   getById GET
 *   edit   PUT
 *   test  GET (It's created just for testing)
 *   addToShelf POST
 *   removeToShelf DELETE
 * */
@RestController
@RequestMapping("/libraries")
@EnableAutoConfiguration
@CrossOrigin
@AllArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    private final LibraryRepository libraryRepository;
    private final LibraryValidation libraryValidation;
    private final ShelfValidation shelfValidation;

    @PostMapping
    public ResponseEntity create(@RequestBody Library library) {
        if (!libraryValidation.existsLibrary(library)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is a library with same id:" + library.getId());
        }

        return ResponseEntity.ok(libraryService.create(library));
    }

    @GetMapping
    public ResponseEntity<List<Library>> getAll(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(libraryService.getAll(name));
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Library library) {
        Library libraryTemp = libraryService.getById(library.getId());
        if (!libraryValidation.notExistsLibrary(library)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is no a library with same id:" + library.getId());
        }

        return ResponseEntity.ok(libraryService.edit(libraryTemp, library));
    }

    @DeleteMapping("/{libraryId}")
    public ResponseEntity delete(@PathVariable Long libraryId) {
        Library library = libraryRepository.findOneById(libraryId);
        if (!libraryValidation.existsLibraryById(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is a library with same id:" + library.getId());
        }

        return ResponseEntity.ok(libraryService.delete(library));
    }

    @GetMapping("/{libraryId}")
    public ResponseEntity getById(@PathVariable Long libraryId) {
        if (!libraryValidation.existsLibraryById(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Library id : " + libraryId);
        }

        return ResponseEntity.ok(libraryService.getById(libraryId));
    }

    @PostMapping("addShelf/{libraryId}")
    public ResponseEntity addToShelf(@PathVariable Long libraryId,
                                     @RequestBody List<Shelf> shelves) {
        if (!libraryValidation.existsLibraryById(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Library id : " + libraryId);
        }

        if (!libraryValidation.libraryCapacityCheckOut(libraryId, shelves)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Library capacity is not enough.");
        }

        return ResponseEntity.ok(libraryService.addToShelf(shelves, libraryId));
    }

    @DeleteMapping("removeShelf/{libraryId}/{shelfIds}")
    public ResponseEntity removeToShelf(@PathVariable Long libraryId,
                                        @PathVariable Long[] shelfIds) {
        if (!libraryValidation.existsLibraryById(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Library id : " + libraryId);
        }

        if (!shelfValidation.existsShelfById(shelfIds)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Shelf id : " + Arrays.toString(shelfIds));
        }

        if (!libraryValidation.existsLibraryRelatedShelf(libraryId, shelfIds)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a related Library with Shelf");
        }

        return ResponseEntity.ok(libraryService.removeToShelf(libraryId, shelfIds));
    }

    @DeleteMapping("removeAllShelf/{libraryId}")
    public ResponseEntity removeToShelf(@PathVariable Long libraryId) {
        if (!libraryValidation.existsLibraryById(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a Library id : " + libraryId);
        }


        if (!libraryValidation.existsLibraryAnyRelatedShelf(libraryId)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("There is not a related Library with Shelf");
        }

        return ResponseEntity.ok(libraryService.removeAllShelf(libraryId));
    }

    @GetMapping("/test")
    public ResponseEntity duplicate() {
        libraryService.duplicate();
        return ResponseEntity.ok("Test");
    }
}
