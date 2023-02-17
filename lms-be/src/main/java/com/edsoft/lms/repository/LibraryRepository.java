package com.edsoft.lms.repository;

import com.edsoft.lms.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findOneById(Long id);
    List<Library> findAllByName(String name);
    List<Library> findAllByOrderById();
    Library findOneByIdAndShelves_Id(Long libraryId, Long shelfId);
}