package com.edsoft.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    private Integer storage;

    @JsonIgnore
    @ManyToOne(targetEntity = Library.class, cascade = {CascadeType.ALL})
    private Library library;
}
