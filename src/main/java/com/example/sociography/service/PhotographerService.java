package com.example.sociography.service;

import com.example.sociography.model.Photographer;
import com.example.sociography.model.Picture;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.repository.PictureRepository;
import com.example.sociography.repository.FollowerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotographerService {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private FollowerRepository followerRepository;

    public List<Photographer> findAll() {
        return photographerRepository.findAll();
    }

    public Page<Photographer> findPaginated(int page, int size) {
        return photographerRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Photographer> findById(Integer id) {
        return photographerRepository.findById(id);
    }

    public Photographer save(Photographer photographer) {
        return photographerRepository.save(photographer);
    }

    public void deleteById(Integer id) {
        photographerRepository.deleteById(id);
    }

    public Photographer getPhotographerByEmail(String email) {
        return photographerRepository.findByEmail(email);
    }

    public List<Picture> getPicturesByPhotographerId(int photographerId) {
        return pictureRepository.findByPhotographerId(photographerId);
    }

//    public int getTotalLikesByPhotographerId(int photographerId) {
//        return pictureRepository.getTotalLikesByPhotographerId(photographerId);
//    }

    public int getFollowersCountByPhotographerId(int photographerId) {
        return followerRepository.countFollowersByPhotographerId(photographerId);
    }

    public int getFollowingCountByPhotographerId(int photographerId) {
        return followerRepository.countFollowingByPhotographerId(photographerId);
    }
}
