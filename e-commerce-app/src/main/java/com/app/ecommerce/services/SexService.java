package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.Sex;
import com.app.ecommerce.repositories.SexRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SexService extends BaseService<Sex, Long> {

    @Autowired
    SexRepository repository;

    @Override
    public Sex findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Sex> findAll() {
        return repository.findAll();
    }

    @Override
    public Sex save(Sex sex) {
        return repository.save(sex);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Sex sex) {
        repository.delete(sex);
    }

    public void replicateData(Map<String, Object> sexData, Envelope.Operation operation) {
        final Sex sex = this.modelMapper().map(sexData, Sex.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(sex.getIdSex());
        } else {
            this.save(sex);
        }
    }

}
