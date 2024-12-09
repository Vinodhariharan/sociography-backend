package com.example.sociography.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sociography.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
