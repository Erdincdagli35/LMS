package com.edsoft.lms.repository;

import com.edsoft.lms.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    Shelf findOneById(Long id);
    List<Shelf> findAllByOrderByIdDesc();
    List<Shelf> findAllByName(String name);
}
