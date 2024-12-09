package com.example.sociography.repository;

import com.example.sociography.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerSignUpRepo extends JpaRepository<Partner, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
