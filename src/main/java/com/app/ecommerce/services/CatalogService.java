package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.repositories.CatalogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> catalogData, Envelope.Operation operation) {
        final Catalog catalog = this.modelMapper().map(catalogData, Catalog.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(catalog.getIdCatalog());
        } else {
            save(catalog);
        }
    }


}
