package com.onlinebookstore.inventory_service.controller;

import com.onlinebookstore.inventory_service.dto.InventoryDto;
import com.onlinebookstore.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        InventoryDto inventory = inventoryService.createInventory(inventoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
    }

    @PutMapping("/add/{bookId}")
    public ResponseEntity<InventoryDto> addInventory( @PathVariable Long bookId ,@RequestBody InventoryDto inventoryDto){
        InventoryDto inventory = inventoryService.addInventory(bookId , inventoryDto);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/reduce/{bookId}")
    public ResponseEntity<InventoryDto> reduceInventory(@PathVariable Long bookId ,@RequestBody InventoryDto inventoryDto){
        InventoryDto inventory = inventoryService.reduceInventory(bookId , inventoryDto);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventory(){
        List<InventoryDto> inventory = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventory);
    }

}
