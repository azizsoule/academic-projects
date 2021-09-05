package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CatalogDTO;
import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService extends BaseService<CatalogDTO, Long> {

    @Autowired
    CatalogRepository repository;

    @Override
    public CatalogDTO findById(Long aLong) {
        Catalog catalog = repository.getById(aLong);
        return modelMapper().map(catalog, CatalogDTO.class);
    }

    @Override
    public List<CatalogDTO> findAll() {
        List<CatalogDTO> catalogDTOS = new ArrayList<>();
        List<Catalog> catalogs = repository.findAll();
        catalogs.forEach(catalog -> {
            catalogDTOS.add(modelMapper().map(catalog, CatalogDTO.class));
        });
        return catalogDTOS;
    }

    @Override
    public CatalogDTO save(CatalogDTO catalogDTO) {
        Catalog catalog = modelMapper().map(catalogDTO, Catalog.class);
        catalog = repository.save(catalog);
        return modelMapper().map(catalog, CatalogDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CatalogDTO catalogDTO) {
        repository.delete(modelMapper().map(catalogDTO, Catalog.class));
    }

}
