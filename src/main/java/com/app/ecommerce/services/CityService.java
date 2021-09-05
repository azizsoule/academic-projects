package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CityDTO;
import com.app.ecommerce.models.City;
import com.app.ecommerce.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService extends BaseService<CityDTO, Long> {

    @Autowired
    CityRepository repository;

    @Override
    public CityDTO findById(Long aLong) {
        City city = repository.getById(aLong);
        return modelMapper().map(city, CityDTO.class);
    }

    @Override
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        List<City> cities = repository.findAll();
        cities.forEach(city -> {
            cityDTOS.add(modelMapper().map(city, CityDTO.class));
        });
        return cityDTOS;
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CityDTO cityDTO) {
        repository.delete(modelMapper().map(cityDTO, City.class));
    }

}
