package com.app.ecommerce.repositories;

import com.app.ecommerce.models.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStateRepository extends JpaRepository<OrderState, String> {
}