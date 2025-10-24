package com.example.warehouse_inventory_api.controller;

import com.example.warehouse_inventory_api.dto.VariantRequest;
import com.example.warehouse_inventory_api.entity.Item;
import com.example.warehouse_inventory_api.entity.Variant;
import com.example.warehouse_inventory_api.repository.ItemRepository;
import com.example.warehouse_inventory_api.repository.VariantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/variants")
public class VariantController {
    private final ItemRepository itemRepo;
    private final VariantRepository variantRepo;

    public VariantController(VariantRepository variantRepo, ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
        this.variantRepo = variantRepo;
    }

    @GetMapping
    public List<Variant> getAll() {
        return variantRepo.findAll();
    }

    @GetMapping("/{id}")
    public Variant getById(@PathVariable Long id) {
        return variantRepo.findById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VariantRequest request) {
        Item item = itemRepo.findById(request.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + request.getItemId()));

        Variant variant = new Variant();
        variant.setItem(item);
        variant.setName(request.getName());
        variant.setSku(request.getSku());
        variant.setPrice(request.getPrice());

        try {
            variant.setAttributes(new ObjectMapper().writeValueAsString(request.getAttributes()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid attributes JSON"));
        }

        Variant saved = variantRepo.save(variant);
        return ResponseEntity.ok(saved);
    }
}

