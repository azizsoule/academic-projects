package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}