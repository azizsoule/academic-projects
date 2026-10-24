package com.app.ecommerce.repositories;

import com.app.ecommerce.models.CustomerPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPaymentMethodRepository extends JpaRepository<CustomerPaymentMethod, Long> {
}