package com.edsoft.lms.controller;

import com.edsoft.lms.model.Library;
import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.LibraryRepository;
import com.edsoft.lms.service.LibraryService;
import com.edsoft.lms.validation.LibraryValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LibraryControllerTest {

    @Mock
    private LibraryService libraryService;

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private LibraryValidation libraryValidation;

    @InjectMocks
    private LibraryController libraryController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(libraryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateLibrary() throws Exception {
        Library library = new Library();
        library.setId(55L);
        library.setName("Test Library");

        when(libraryValidation.existsLibrary(any(Library.class))).thenReturn(false);
        when(libraryService.create(any(Library.class))).thenReturn(library.getId());

        mockMvc.perform(post("/libraries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(library)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(55L))
                .andExpect(jsonPath("$.name").value("Test Library"));
    }

    @Test
    public void testGetAllLibraries() throws Exception {
        Library library = new Library();
        library.setId(1L);
        library.setName("Test Library");

        when(libraryService.getAll(any())).thenReturn(Collections.singletonList(library));

        mockMvc.perform(get("/libraries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Library"));
    }

    @Test
    public void testEditLibrary() throws Exception {
        Library library = new Library();
        library.setId(1L);
        library.setName("Updated Library");

        when(libraryService.getById(anyLong())).thenReturn(library);
        when(libraryValidation.notExistsLibrary(any(Library.class))).thenReturn(true);
        when(libraryService.edit(any(Library.class), any(Library.class))).thenReturn(library);

        mockMvc.perform(put("/libraries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(library)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Library"));
    }

    @Test
    public void testDeleteLibrary() throws Exception {
        Library library = new Library();
        library.setId(10L);
        library.setName("Test Library");

        when(libraryRepository.findOneById(anyLong())).thenReturn(library);
        when(libraryValidation.existsLibraryById(anyLong())).thenReturn(true);
        when(libraryService.delete(any(Library.class))).thenReturn(library.getId());

        mockMvc.perform(delete("/libraries/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("Test Library"));
    }

    @Test
    public void testGetLibraryById() throws Exception {
        Library library = new Library();
        library.setId(1L);
        library.setName("Test Library");

        when(libraryValidation.existsLibraryById(anyLong())).thenReturn(true);
        when(libraryService.getById(anyLong())).thenReturn(library);

        mockMvc.perform(get("/libraries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Library"));
    }

    @Test
    public void testAddToShelf() throws Exception {
        Shelf shelf = new Shelf();
        shelf.setId(1L);
        shelf.setName("Test Shelf");

        when(libraryValidation.existsLibraryById(anyLong())).thenReturn(true);
        when(libraryValidation.notEnterZeroForStorage(any(Shelf.class))).thenReturn(true);
        when(libraryValidation.libraryCapacityCheckOut(anyLong(), any(Shelf.class))).thenReturn(true);
        when(libraryService.addToShelf(any(Shelf.class), anyLong())).thenReturn(shelf.getId());

        mockMvc.perform(post("/libraries/addShelf/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shelf)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Shelf"));
    }

    @Test
    public void testRemoveToShelf() throws Exception {
        Library library = new Library();
        library.setId(1L);
        library.setName("Test Library");

        when(libraryValidation.existsLibraryById(anyLong())).thenReturn(true);
        when(libraryValidation.existsLibraryAnyRelatedShelf(anyLong())).thenReturn(true);
        when(libraryService.removeAllShelf(anyLong())).thenReturn((List<Long>) library);

        mockMvc.perform(delete("/libraries/removeAllShelf/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Library"));
    }
}
