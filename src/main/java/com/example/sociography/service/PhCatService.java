package com.example.sociography.service;

import com.example.sociography.model.PhCat;
import com.example.sociography.model.PhCatId;
import com.example.sociography.repository.PhCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhCatService {

    @Autowired
    private PhCatRepository phCatRepository;

    public List<PhCat> findAll() {
        return phCatRepository.findAll();
    }

    public Optional<PhCat> findById(PhCatId id) {
        return phCatRepository.findById(id);
    }

    public PhCat save(PhCat phCat) {
        return phCatRepository.save(phCat);
    }

    public void deleteById(PhCatId id) {
        phCatRepository.deleteById(id);
    }
}
