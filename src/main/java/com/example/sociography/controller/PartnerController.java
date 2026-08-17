package com.example.sociography.controller;

import com.example.sociography.model.Partner;
import com.example.sociography.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.findAll();
    }

    @GetMapping("/page")
    public Page<Partner> getPaginatedPartners(@RequestParam int page, @RequestParam int size) {
        return partnerService.findPaginated(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable Integer id) {
        return partnerService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
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

