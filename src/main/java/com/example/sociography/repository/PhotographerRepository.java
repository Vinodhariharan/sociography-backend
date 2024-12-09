package com.example.sociography.repository;

import com.example.sociography.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Integer> {
    Photographer findByUsername(String username);
    
    Photographer findByEmail(String email);

}
