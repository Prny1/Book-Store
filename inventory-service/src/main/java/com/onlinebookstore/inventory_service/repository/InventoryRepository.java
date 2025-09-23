package com.onlinebookstore.inventory_service.repository;

import com.onlinebookstore.inventory_service.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    InventoryEntity findByBookId(Long bookId);
}
