package com.example.sociography.service;

import com.example.sociography.model.PicCat;
import com.example.sociography.model.PicCatId;
import com.example.sociography.repository.PicCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PicCatService {

    @Autowired
    private PicCatRepository picCatRepository;

    public List<PicCat> findAll() {
        return picCatRepository.findAll();
    }

    public Optional<PicCat> findById(PicCatId id) {
        return picCatRepository.findById(id);
    }

    public PicCat save(PicCat picCat) {
        return picCatRepository.save(picCat);
    }

    public void deleteById(PicCatId id) {
        picCatRepository.deleteById(id);
    }
}
