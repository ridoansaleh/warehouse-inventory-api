package com.example.warehouse_inventory_api.dto;

import java.math.BigDecimal;
import java.util.Map;

public class VariantRequest {
    private Long itemId;
    private String name;
    private String sku;
    private BigDecimal price;
    private Map<String, Object> attributes; // JSON attributes

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}

