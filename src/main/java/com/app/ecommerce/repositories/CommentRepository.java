package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlockedIsTrue();
    List<Comment> findByBlockedIsFalse();
    @Query("UPDATE Comment c SET c.blocked = ?1 WHERE c.idComment = ?2")
    @Modifying
    void block(boolean bool,Long idComment);
}