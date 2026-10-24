package com.app.ecommerce.utils;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.DictionaryRepository;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CommentFilter {

    private final DictionaryRepository dictionaryRepository;

    public CommentFilter(@Lazy DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @PrePersist
    @PreUpdate
    public void filter(final Comment comment) {
        int i = (int) dictionaryRepository.findAll().stream().filter(word -> comment.getContent().contains(word.getLabel())).count();
        comment.setBlocked(i > 0);
    }

}
