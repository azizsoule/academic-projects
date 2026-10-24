package com.app.ecommerce.services;

import io.debezium.data.Envelope;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public abstract class BaseService<ENTITY, ID> {

    private final ModelMapper modelMapper = new ModelMapper();

    public ModelMapper modelMapper() {
        return modelMapper;
    }

    public abstract ENTITY findById(ID id);

    public abstract List<ENTITY> findAll();

    public abstract ENTITY save(ENTITY entity);

    public ENTITY update(ENTITY entity) {
        return this.save(entity);
    }

    public abstract void deleteById(ID id);

    public abstract void delete(ENTITY entity);

}
