package com.app.ecommerce.repositories;

import com.app.ecommerce.models.supers.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}