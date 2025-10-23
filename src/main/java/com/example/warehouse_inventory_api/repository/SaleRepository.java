package com.example.warehouse_inventory_api.repository;

import com.example.warehouse_inventory_api.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}