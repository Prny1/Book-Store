package com.onlinebookstore.inventory_service.service;

import com.onlinebookstore.inventory_service.dto.InventoryDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface InventoryService {

    public InventoryDto createInventory(InventoryDto inventoryDto);

    public InventoryDto addInventory(Long bookId , InventoryDto inventoryDto);

    public InventoryDto reduceInventory( Long bookId ,InventoryDto inventoryDto);

    public List<InventoryDto> getAllInventory();
}
