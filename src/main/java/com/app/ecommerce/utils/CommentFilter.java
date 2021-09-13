package com.app.ecommerce.utils;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.DictionaryRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class CommentFilter {

    private final DictionaryRepository dictionaryService;

    public CommentFilter(@Lazy DictionaryRepository repository) {
        this.dictionaryService = repository;
    }

    @PrePersist
    @PreUpdate
    public void filter(final Comment comment){
        int i = (int) dictionaryService.findAll().stream().filter(word -> comment.getContent().contains(word.getLabel())).count();
        comment.setBlocked(i > 0);
    }
}
