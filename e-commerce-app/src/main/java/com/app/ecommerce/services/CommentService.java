package com.app.ecommerce.services;

import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.CommentRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Comment comment = this.modelMapper().map(data, Comment.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(comment.getIdComment());
        } else {
            save(comment);
        }
    }

}
