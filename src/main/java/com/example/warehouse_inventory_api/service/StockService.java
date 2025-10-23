package com.example.warehouse_inventory_api.service;

import org.springframework.stereotype.Service;
import com.example.warehouse_inventory_api.repository.StockLevelRepository;
import com.example.warehouse_inventory_api.repository.VariantRepository;
import com.example.warehouse_inventory_api.repository.SaleRepository;
import com.example.warehouse_inventory_api.dto.SaleResponse;
import com.example.warehouse_inventory_api.entity.Sale;
import com.example.warehouse_inventory_api.entity.StockLevel;
import com.example.warehouse_inventory_api.entity.Variant;


@Service
public class StockService {

    private final StockLevelRepository stockRepo;
    private final VariantRepository variantRepo;
    private final SaleRepository saleRepo;

    public StockService(StockLevelRepository stockRepo, VariantRepository variantRepo, SaleRepository saleRepo) {
        this.stockRepo = stockRepo;
        this.variantRepo = variantRepo;
        this.saleRepo = saleRepo;
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

        Variant variant = variantRepo.findById(variantId)
                .orElseThrow(() -> new IllegalStateException("Variant not found"));
        Sale sale = new Sale(variant, quantityToSell);
        saleRepo.save(sale);

        return new SaleResponse(
            "Sale recorded successfully.",
            variantId,
            quantityToSell,
            stock.getQuantity()
        );
    }
}

