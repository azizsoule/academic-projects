package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = "SELECT * FROM ORDER_DETAIL ORDER BY created_at DESC",
            nativeQuery = true)
    List<Order> findRecentOrders();

}