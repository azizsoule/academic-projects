package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService extends BaseService<Catalog, Long> {

    @Autowired
    CatalogRepository repository;

    @Override
    public Catalog findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Catalog> findAll() {
        return repository.findAll();
    }

    @Override
    public Catalog save(Catalog catalog) {
        return repository.save(catalog);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Catalog Catalog) {
        repository.delete(modelMapper().map(Catalog, Catalog.class));
    }

}
