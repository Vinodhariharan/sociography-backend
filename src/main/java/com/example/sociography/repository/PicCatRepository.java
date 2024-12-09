package com.example.sociography.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sociography.model.PicCat;
import com.example.sociography.model.PicCatId;

public interface PicCatRepository extends JpaRepository<PicCat, PicCatId> {
}
