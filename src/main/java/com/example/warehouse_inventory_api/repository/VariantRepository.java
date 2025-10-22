package com.example.warehouse_inventory_api.repository;

import com.example.warehouse_inventory_api.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {}
