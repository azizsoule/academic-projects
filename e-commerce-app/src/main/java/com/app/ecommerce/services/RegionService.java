package com.app.ecommerce.services;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Region;
import com.app.ecommerce.repositories.RegionRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegionService extends BaseService<Region, Long> {

    @Autowired
    RegionRepository repository;

    @Override
    public Region findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Region> findAll() {
        return repository.findAll();
    }

    @Override
    public Region save(Region region) {
        return repository.save(region);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Region region) {
        repository.delete(region);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Region region = this.modelMapper().map(data, Region.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(region.getIdRegion());
        } else {
            this.save(region);
        }
    }

}
