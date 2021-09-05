package com.app.ecommerce.services;

import com.app.ecommerce.dtos.RegionDTO;
import com.app.ecommerce.models.Region;
import com.app.ecommerce.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService extends BaseService<RegionDTO, Long> {

    @Autowired
    RegionRepository repository;

    @Override
    public RegionDTO findById(Long aLong) {
        Region region = repository.getById(aLong);
        return modelMapper().map(region, RegionDTO.class);
    }

    @Override
    public List<RegionDTO> findAll() {
        List<RegionDTO> regionDTOS = new ArrayList<>();
        List<Region> regions = repository.findAll();
        regions.forEach(region -> {
            regionDTOS.add(modelMapper().map(region, RegionDTO.class));
        });
        return regionDTOS;
    }

    @Override
    public RegionDTO save(RegionDTO regionDTO) {
        Region region = repository.save(modelMapper().map(regionDTO, Region.class));
        return modelMapper().map(region, RegionDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(RegionDTO regionDTO) {
        repository.delete(modelMapper().map(regionDTO, Region.class));
    }

}
