package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}