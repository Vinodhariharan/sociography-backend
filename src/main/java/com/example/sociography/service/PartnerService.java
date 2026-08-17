package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }

    public Page<Partner> findPaginated(int page, int size) {
        return partnerRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Partner> findById(Integer id) {
        return partnerRepository.findById(id);
    }

    public Partner save(Partner partner) {
        return partnerRepository.save(partner);
    }

    public void deleteById(Integer id) {
        partnerRepository.deleteById(id);
    }
}
