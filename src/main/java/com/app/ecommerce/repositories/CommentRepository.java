package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlockedIsTrue();
}