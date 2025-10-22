package com.example.warehouse_inventory_api.controller;

import com.example.warehouse_inventory_api.entity.Variant;
import com.example.warehouse_inventory_api.repository.VariantRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {
    private final VariantRepository variantRepo;

    public VariantController(VariantRepository variantRepo) {
        this.variantRepo = variantRepo;
    }

    @GetMapping
    public List<Variant> getAll() {
        return variantRepo.findAll();
    }

    @PostMapping
    public Variant create(@RequestBody Variant variant) {
        return variantRepo.save(variant);
    }

    @GetMapping("/{id}")
    public Variant getById(@PathVariable Long id) {
        return variantRepo.findById(id).orElseThrow();
    }
}

