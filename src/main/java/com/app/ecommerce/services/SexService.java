package com.app.ecommerce.services;

import com.app.ecommerce.dtos.SexDTO;
import com.app.ecommerce.models.Sex;
import com.app.ecommerce.repositories.SexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SexService extends BaseService<SexDTO, Long> {

    @Autowired
    SexRepository repository;

    @Override
    public SexDTO findById(Long aLong) {
        Sex sex = repository.getById(aLong);
        return modelMapper().map(sex, SexDTO.class);
    }

    @Override
    public List<SexDTO> findAll() {
        List<SexDTO> sexDTOS = new ArrayList<>();
        List<Sex> sexes = repository.findAll();
        sexes.forEach(sex -> {
            sexDTOS.add(modelMapper().map(sex, SexDTO.class));
        });
        return sexDTOS;
    }

    @Override
    public SexDTO save(SexDTO sexDTO) {
        Sex sex = repository.save(modelMapper().map(sexDTO, Sex.class));
        return modelMapper().map(sex, SexDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(SexDTO sexDTO) {
        repository.delete(modelMapper().map(sexDTO, Sex.class));
    }

}
