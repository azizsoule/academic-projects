package com.app.ecommerce.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService<DTO, ID> {

    private final ModelMapper modelMapper = new ModelMapper();

    public ModelMapper modelMapper() {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    public abstract DTO findById(ID id);

    public abstract List<DTO> findAll();

    public abstract DTO save(DTO dto);

    public DTO update(DTO dto) {
        return this.save(dto);
    }

    public abstract void deleteById(ID id);

    public abstract void delete(DTO dto);

}
