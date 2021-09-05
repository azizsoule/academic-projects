package com.app.ecommerce.services;

import com.app.ecommerce.dtos.DictionaryDTO;
import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.repositories.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryService extends BaseService<DictionaryDTO, Long> {

    @Autowired
    DictionaryRepository repository;

    @Override
    public DictionaryDTO findById(Long aLong) {
        Dictionary dictionary = repository.getById(aLong);
        return modelMapper().map(dictionary, DictionaryDTO.class);
    }

    @Override
    public List<DictionaryDTO> findAll() {
        List<DictionaryDTO> dictionaryDTOS = new ArrayList<>();
        List<Dictionary> dictionaries = repository.findAll();
        dictionaries.forEach(dictionary -> {
            dictionaryDTOS.add(modelMapper().map(dictionary, DictionaryDTO.class));
        });
        return dictionaryDTOS;
    }

    @Override
    public DictionaryDTO save(DictionaryDTO dictionaryDTO) {
        Dictionary dictionary = repository.save(modelMapper().map(dictionaryDTO, Dictionary.class));
        return modelMapper().map(dictionary, DictionaryDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(DictionaryDTO dictionaryDTO) {
        repository.delete(modelMapper().map(dictionaryDTO, Dictionary.class));
    }
}
