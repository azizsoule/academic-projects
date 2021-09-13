package com.app.ecommerce.services;

import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.repositories.CommentRepository;
import com.app.ecommerce.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DictionaryService extends BaseService<Dictionary, Long> {

    @Autowired
    DictionaryRepository repository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Dictionary findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Dictionary> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Dictionary save(Dictionary dictionary) {
        Dictionary savedDico = repository.save(dictionary);
//        if (commentRepository.count() != 0) {
//            commentRepository.findAll().forEach(comment -> {
//                if (comment.getContent().contains(savedDico.getLabel())) {
//                    if (!comment.isBlocked()) {
//                        comment.setBlocked(true);
//                    }
//                    commentRepository.save(comment);
//                }
//            });
//        }
        return savedDico;
    }
    public void saveAll(Iterable<Dictionary> dictionary) {
        repository.saveAll(dictionary);
    }


    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Dictionary dictionary) {
        repository.delete(modelMapper().map(dictionary, Dictionary.class));
    }
}
