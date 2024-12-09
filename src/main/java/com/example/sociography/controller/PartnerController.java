package com.example.sociography.controller;

import com.example.sociography.model.Partner;
import com.example.sociography.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Partner>> getPartnerById1(@PathVariable Integer id) {
        Optional<Partner> partner = partnerService.findById(id); // Fetch partner by ID
        if (partner != null) {
            return ResponseEntity.ok(partner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Partner createPartner(@RequestBody Partner partner) {
        return partnerService.save(partner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable Integer id, @RequestBody Partner updatedPartner) {
        return partnerService.findById(id)
            .map(existingPartner -> {
                updatedPartner.setId(existingPartner.getId());
                return ResponseEntity.ok(partnerService.save(updatedPartner));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePartner(@PathVariable Integer id) {
        return partnerService.findById(id)
            .map(existingPartner -> {
                partnerService.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

