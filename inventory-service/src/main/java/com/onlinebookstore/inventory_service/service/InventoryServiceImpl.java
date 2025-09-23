package com.onlinebookstore.inventory_service.service;

import com.onlinebookstore.inventory_service.dto.InventoryDto;
import com.onlinebookstore.inventory_service.entity.InventoryEntity;
import com.onlinebookstore.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        InventoryEntity inventory = modelMapper.map(inventoryDto , InventoryEntity.class);
        inventoryRepository.save(inventory);

        return modelMapper.map(inventory , InventoryDto.class);
    }

    @Override
    public InventoryDto addInventory(Long bookId, InventoryDto inventoryDto) {

        if(!bookId.equals(inventoryDto.getBookId())) {
            throw new RuntimeException("Book does not match");
        }

        InventoryEntity inventory = inventoryRepository.findByBookId(bookId);


        if (inventory == null) {
            throw new RuntimeException("Inventory not found for bookId: " + bookId);
        }
        inventory.setQuantity(inventory.getQuantity() + inventoryDto.getQuantity());
        inventoryRepository.save(inventory);
        return modelMapper.map(inventory , InventoryDto.class);
    }

    @Override
    public InventoryDto reduceInventory(Long bookId, InventoryDto inventoryDto) {

        if(!bookId.equals(inventoryDto.getBookId())) {
            throw new RuntimeException("Book does not match");
        }

        InventoryEntity inventory = inventoryRepository.findByBookId(bookId);

        if (inventory == null) {
            throw new RuntimeException("Inventory not found for bookId: " + bookId);
        }

        if(inventory.getQuantity() < inventoryDto.getQuantity()){
            throw new RuntimeException("Not enough quantity");
        }

        inventory.setQuantity(inventory.getQuantity() - inventoryDto.getQuantity());
        inventoryRepository.save(inventory);
        return modelMapper.map(inventory , InventoryDto.class);
    }

    @Override
    public List<InventoryDto> getAllInventory() {

        List<InventoryEntity> inventoryEntities = inventoryRepository.findAll();
        return inventoryEntities.stream()
                .map(inventory -> modelMapper.map(inventory , InventoryDto.class))
                .toList();
    }
}
