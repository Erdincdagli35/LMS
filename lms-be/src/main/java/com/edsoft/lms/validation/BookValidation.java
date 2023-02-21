package com.edsoft.lms.validation;

import com.edsoft.lms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidation {

    @Autowired
    BookRepository bookRepository;

    public boolean existsShelfById(Long id) {
        return bookRepository.findOneById(id) != null;
    }
}
