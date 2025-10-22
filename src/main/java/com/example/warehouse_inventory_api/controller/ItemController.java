package com.example.warehouse_inventory_api.controller;

import com.example.warehouse_inventory_api.entity.Item;
import com.example.warehouse_inventory_api.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemRepository itemRepo;

    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemRepo.save(item);
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable Long id) {
        return itemRepo.findById(id).orElseThrow();
    }
}
