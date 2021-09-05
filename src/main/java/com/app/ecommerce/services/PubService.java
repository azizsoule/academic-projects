package com.app.ecommerce.services;

import com.app.ecommerce.dtos.PubDTO;
import com.app.ecommerce.models.Pub;
import com.app.ecommerce.repositories.PubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PubService extends BaseService<PubDTO, Long> {

    @Autowired
    PubRepository repository;

    @Override
    public PubDTO findById(Long aLong) {
        Pub pub = repository.getById(aLong);
        return modelMapper().map(pub, PubDTO.class);
    }

    @Override
    public List<PubDTO> findAll() {
        List<PubDTO> pubDTOS = new ArrayList<>();
        List<Pub> pubs = repository.findAll();
        pubs.forEach(pub -> {
            pubDTOS.add(modelMapper().map(pub, PubDTO.class));
        });
        return pubDTOS;
    }

    @Override
    public PubDTO save(PubDTO pubDTO) {
        Pub pub = repository.save(modelMapper().map(pubDTO, Pub.class));
        return modelMapper().map(pub, PubDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(PubDTO pubDTO) {
        repository.delete(modelMapper().map(pubDTO, Pub.class));
    }

}
