package com.example.warehouse_inventory_api.dto;

public class SaleResponse {
    private String message;
    private Long variantId;
    private int quantitySold;
    private int remainingStock;

    public SaleResponse(String message, Long variantId, int quantitySold, int remainingStock) {
        this.message = message;
        this.variantId = variantId;
        this.quantitySold = quantitySold;
        this.remainingStock = remainingStock;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }
}
