package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}