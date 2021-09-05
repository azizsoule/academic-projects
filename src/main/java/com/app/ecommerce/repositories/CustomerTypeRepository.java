package com.app.ecommerce.repositories;

import com.app.ecommerce.models.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
}
