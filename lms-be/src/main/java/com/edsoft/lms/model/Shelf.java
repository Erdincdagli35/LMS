package com.edsoft.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer storage;

    @JsonIgnore
    @ManyToOne(targetEntity = Library.class, cascade = {CascadeType.ALL})
    private Library library;
    /*
        @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Book> books = new ArrayList<>();
    */
    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        book.setShelf(this);
    }


}
