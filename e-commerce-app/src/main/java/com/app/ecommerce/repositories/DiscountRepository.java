package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}