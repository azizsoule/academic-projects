package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CommentDTO;
import com.app.ecommerce.models.Comment;
import com.app.ecommerce.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService extends BaseService<CommentDTO, Long> {

    @Autowired
    CommentRepository repository;

    @Override
    public CommentDTO findById(Long aLong) {
        Comment comment = repository.getById(aLong);
        return modelMapper().map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> findAll() {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        List<Comment> comments = repository.findAll();
        comments.forEach(comment -> {
            commentDTOS.add(modelMapper().map(comment, CommentDTO.class));
        });
        return commentDTOS;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = modelMapper().map(commentDTO, Comment.class);
        comment = repository.save(comment);
        return modelMapper().map(comment, CommentDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CommentDTO commentDTO) {
        repository.delete(modelMapper().map(commentDTO, Comment.class));
    }

}
