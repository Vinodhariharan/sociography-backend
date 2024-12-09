package com.example.sociography.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sociography.model.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
    Partner findByUsername(String username);
    Partner findByEmail(String email);
}
