package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
