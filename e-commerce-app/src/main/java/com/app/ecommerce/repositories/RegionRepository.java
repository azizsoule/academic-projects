package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}