package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.repositories.DictionaryRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DictionaryService extends BaseService<Dictionary, Long> {

    @Autowired
    DictionaryRepository repository;

    @Override
    public Dictionary findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Dictionary> findAll() {
        return repository.findAll();
    }

    @Override
    public Dictionary save(Dictionary dictionary) {
        return repository.save(dictionary);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Dictionary dictionary) {
        repository.delete(dictionary);
    }

    public void replicateData(Map<String, Object> dictionaryData, Envelope.Operation operation) {
        final Dictionary dictionary = this.modelMapper().map(dictionaryData, Dictionary.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(dictionary.getIdWord());
        } else {
            this.save(dictionary);
        }
    }

}
