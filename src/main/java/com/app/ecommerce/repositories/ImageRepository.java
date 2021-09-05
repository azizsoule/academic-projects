package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
