package com.example.sociography.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sociography.model.PhCat;
import com.example.sociography.model.PhCatId;

public interface PhCatRepository extends JpaRepository<PhCat, PhCatId> {
}
