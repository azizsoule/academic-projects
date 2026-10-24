package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexRepository extends JpaRepository<Sex, Long> {
}