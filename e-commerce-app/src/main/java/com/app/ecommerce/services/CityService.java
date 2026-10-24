package com.app.ecommerce.services;

import com.app.ecommerce.models.City;
import com.app.ecommerce.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

}
