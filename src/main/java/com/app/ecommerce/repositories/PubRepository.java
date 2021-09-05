package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Pub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PubRepository extends JpaRepository<Pub, Long> {
}
