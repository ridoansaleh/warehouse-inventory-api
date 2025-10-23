package com.example.warehouse_inventory_api.repository;

import java.util.Optional;
import com.example.warehouse_inventory_api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {
    Optional<StockLevel> findByVariantId(Long variantId);
}

