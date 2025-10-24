package com.example.warehouse_inventory_api.dto;

public class StockLevelRequest {
    private Long variantId;
    private int quantity;

    // Getters and setters
    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

