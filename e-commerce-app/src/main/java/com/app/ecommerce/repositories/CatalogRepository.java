package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}