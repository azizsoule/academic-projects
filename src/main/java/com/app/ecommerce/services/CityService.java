package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.City;
import com.app.ecommerce.repositories.CityRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityService extends BaseService<City, Long> {

    @Autowired
    CityRepository repository;

    @Override
    public City findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(City city) {
        repository.delete(city);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final City city = this.modelMapper().map(data, City.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(city.getIdCity());
        } else {
            this.save(city);
        }
    }

}
