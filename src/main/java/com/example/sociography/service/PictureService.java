package com.example.sociography.service;

import com.example.sociography.model.Picture;
import com.example.sociography.model.Category;
import com.example.sociography.model.PicCat;
import com.example.sociography.model.PicCatId;
import com.example.sociography.repository.PictureRepository;
import com.example.sociography.repository.CategoryRepository;
import com.example.sociography.repository.PicCatRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PicCatRepository picCatRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(PictureService.class);


    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    public Optional<Picture> findById(Integer id) {
        return pictureRepository.findById(id);
    }

    public Page<Picture> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pictureRepository.findAll(pageable);
    }

    public Picture save(Picture picture, List<String> categories) {
        Picture savedPicture = pictureRepository.save(picture);

        for (String categoryName : categories) {
            Category category = categoryRepository.findByName(categoryName);
            if (category == null) {
                category = new Category();
                category.setName(categoryName);
                category = categoryRepository.save(category);
            }
            PicCat picCat = new PicCat(new PicCatId(savedPicture.getId(), category.getId()), savedPicture, category);
            picCatRepository.save(picCat);
        }

        return savedPicture;
    }

    public void deleteById(Integer id) {
        pictureRepository.deleteById(id);
    }
    
    public List<Picture> searchPicturesByDescription(String keyword) {
        logger.debug("Searching pictures with keyword: {}", keyword);
        List<Picture> results = pictureRepository.findByDescriptionContaining(keyword);
        logger.debug("Found {} pictures", results.size());
        return results;
    }
}
