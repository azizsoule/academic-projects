package com.app.ecommerce.services;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends BaseService<Comment, Long> {

    @Autowired
    CommentRepository repository;

    @Override
    public Comment findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }

}
