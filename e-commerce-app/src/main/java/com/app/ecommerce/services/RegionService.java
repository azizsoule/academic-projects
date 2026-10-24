package com.app.ecommerce.services;

import com.app.ecommerce.models.Region;
import com.app.ecommerce.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService{

    @Autowired
    RegionRepository repository;

    public Region findById(Long aLong) {
        Region region = repository.getById(aLong);
        return region;
    }

    public List<Region> findAll() {
        return repository.findAll();
    }

    public Region save(Region region) {
        return repository.save(region);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
    public void delete(Region region) {
        repository.delete(region);
    }

}
