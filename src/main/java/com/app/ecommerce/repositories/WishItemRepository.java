package com.app.ecommerce.repositories;

import com.app.ecommerce.models.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {
}