package com.app.ecommerce.services;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Pub;
import com.app.ecommerce.repositories.PubRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PubService extends BaseService<Pub, Long> {

    @Autowired
    PubRepository repository;

    @Override
    public Pub findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Pub> findAll() {
        return repository.findAll();
    }

    @Override
    public Pub save(Pub pub) {
        return repository.save(pub);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Pub pub) {
        repository.delete(pub);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Pub pub = this.modelMapper().map(data, Pub.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(pub.getIdPub());
        } else {
            this.save(pub);
        }
    }

}
