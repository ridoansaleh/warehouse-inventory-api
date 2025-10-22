package com.example.warehouse_inventory_api.controller;

import com.example.warehouse_inventory_api.entity.StockLevel;
import com.example.warehouse_inventory_api.repository.StockLevelRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockLevelController {
    private final StockLevelRepository stockRepo;

    public StockLevelController(StockLevelRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    @GetMapping
    public List<StockLevel> getAll() {
        return stockRepo.findAll();
    }

    @PostMapping
    public StockLevel create(@RequestBody StockLevel stock) {
        return stockRepo.save(stock);
    }

    @PutMapping("/{id}")
    public StockLevel update(@PathVariable Long id, @RequestBody StockLevel updatedStock) {
        StockLevel stock = stockRepo.findById(id).orElseThrow();
        stock.setQuantity(updatedStock.getQuantity());
        return stockRepo.save(stock);
    }
}
