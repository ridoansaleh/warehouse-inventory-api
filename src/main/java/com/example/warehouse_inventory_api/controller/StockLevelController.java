package com.example.warehouse_inventory_api.controller;

import com.example.warehouse_inventory_api.dto.StockLevelRequest;
import com.example.warehouse_inventory_api.entity.StockLevel;
import com.example.warehouse_inventory_api.entity.Variant;
import com.example.warehouse_inventory_api.repository.StockLevelRepository;
import com.example.warehouse_inventory_api.repository.VariantRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock-levels")
public class StockLevelController {
    private final StockLevelRepository stockRepo;
    private final VariantRepository variantRepo;

    public StockLevelController(StockLevelRepository stockRepo, VariantRepository variantRepo) {
        this.stockRepo = stockRepo;
        this.variantRepo = variantRepo;
    }

    @GetMapping
    public List<StockLevel> getAll() {
        return stockRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateStock(@RequestBody StockLevelRequest request) {
        if (request.getQuantity() < 0) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Quantity cannot be negative")
            );
        }

        Variant variant = variantRepo.findById(request.getVariantId())
                .orElseThrow(() -> new IllegalArgumentException("Variant not found"));

        StockLevel stock = stockRepo.findByVariantId(request.getVariantId())
                .orElseGet(() -> {
                    StockLevel newStock = new StockLevel();
                    newStock.setVariant(variant);
                    newStock.setQuantity(request.getQuantity());
                    return newStock;
                });

        stock.setQuantity(request.getQuantity());
        StockLevel savedStock = stockRepo.save(stock);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Stock updated successfully");
        response.put("variantId", savedStock.getVariant().getId());
        response.put("newQuantity", savedStock.getQuantity());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StockLevel updatedStock) {
        if (updatedStock.getQuantity() < 0) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Quantity cannot be negative")
            );
        }

        StockLevel stock = stockRepo.findById(id).orElseThrow();
        stock.setQuantity(updatedStock.getQuantity());
        StockLevel savedStock = stockRepo.save(stock);

        return ResponseEntity.ok(savedStock);
    }
}
