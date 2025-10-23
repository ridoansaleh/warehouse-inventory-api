package com.example.warehouse_inventory_api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.warehouse_inventory_api.dto.SaleRequest;
import com.example.warehouse_inventory_api.dto.SaleResponse;
import com.example.warehouse_inventory_api.service.StockService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final StockService stockService;

    public SaleController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<?> sellItem(@RequestBody SaleRequest saleRequest) {
        try {
            SaleResponse response = stockService.processSale(
                saleRequest.getVariantId(),
                saleRequest.getQuantity()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }
}
