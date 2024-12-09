package com.example.sociography.repository;

import com.example.sociography.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpPhotographerRepo extends JpaRepository<Photographer, Integer> {
    boolean existsByUsername(String phUsername);
    boolean existsByEmail(String phEmail);
}
