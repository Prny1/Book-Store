package com.onlinebookstore.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryDto {

    private Long id;
    private Long bookId;
    private Long quantity;
}
