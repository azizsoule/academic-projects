package com.app.ecommerce.repositories;

import com.app.ecommerce.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}