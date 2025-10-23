package com.example.warehouse_inventory_api.service;

import org.springframework.stereotype.Service;
import com.example.warehouse_inventory_api.repository.StockLevelRepository;
import com.example.warehouse_inventory_api.dto.SaleResponse;
import com.example.warehouse_inventory_api.entity.StockLevel;


@Service
public class StockService {

    private final StockLevelRepository stockRepo;

    public StockService(StockLevelRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    public SaleResponse processSale(Long variantId, int quantityToSell) {
        StockLevel stock = stockRepo.findByVariantId(variantId)
                .orElseThrow(() -> new IllegalStateException("Variant ID " + variantId + " not found"));

        if (stock.getQuantity() < quantityToSell) {
            throw new IllegalStateException("Insufficient stock for variant ID " + variantId +
                    ". Available: " + stock.getQuantity() + ", Requested: " + quantityToSell);
        }

        stock.setQuantity(stock.getQuantity() - quantityToSell);
        stockRepo.save(stock);

        return new SaleResponse(
            "Sale recorded successfully.",
            variantId,
            quantityToSell,
            stock.getQuantity()
        );
    }
}

