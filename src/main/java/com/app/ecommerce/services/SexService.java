package com.app.ecommerce.services;

import com.app.ecommerce.models.Sex;
import com.app.ecommerce.repositories.SexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
