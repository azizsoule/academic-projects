package com.app.ecommerce.utils;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.DictionaryRepository;
import com.app.ecommerce.services.CommentService;
import com.app.ecommerce.services.DictionaryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class FilterSytem {

    private final DictionaryRepository dictionaryService;

    public FilterSytem(@Lazy DictionaryRepository repository) {
        this.dictionaryService = repository;
    }

    @PrePersist
    @PreUpdate
    public void filter(final Comment comment){
        int i = (int) dictionaryService.findAll().stream().filter(word -> comment.getContent().contains(word.getLabel())).count();
        comment.setBlocked(i > 0);
    }
}
