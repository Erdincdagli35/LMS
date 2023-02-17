package com.edsoft.lms.service.implementation;

import com.edsoft.lms.model.Shelf;
import com.edsoft.lms.repository.ShelfRepository;
import com.edsoft.lms.service.ShelfService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class ShelfServiceImp implements ShelfService {
    private final ShelfRepository shelfRepository;

    @Override
    public List<Shelf> getAll(String name) {
        return name == null ? shelfRepository.findAllByOrderByIdDesc() : shelfRepository.findAllByName(name);
    }

    @Override
    public Shelf edit(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    @Override
    public Long delete(Shelf shelf) {
        shelfRepository.delete(shelf);
        return shelf.getId();
    }

    @Override
    public Shelf getById(Long id) {
        return shelfRepository.findOneById(id);
    }

    @Override
    public List<Shelf> getByIds(Long[] ids) {
        List<Shelf> shelves = new ArrayList<>();
        for (Long id : ids) shelves.add(getById(id));
        return shelves;
    }
}
