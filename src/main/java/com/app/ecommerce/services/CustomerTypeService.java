package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CustomerTypeDTO;
import com.app.ecommerce.models.CustomerType;
import com.app.ecommerce.repositories.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerTypeService extends BaseService<CustomerTypeDTO, Long> {

    @Autowired
    CustomerTypeRepository repository;

    @Override
    public CustomerTypeDTO findById(Long aLong) {
        CustomerType customerType = repository.getById(aLong);
        return modelMapper().map(customerType, CustomerTypeDTO.class);
    }

    @Override
    public List<CustomerTypeDTO> findAll() {
        List<CustomerTypeDTO> customerTypeDTOS = new ArrayList<>();
        List<CustomerType> customerTypes = repository.findAll();
        customerTypes.forEach(customerType -> {
            customerTypeDTOS.add(modelMapper().map(customerType, CustomerTypeDTO.class));
        });
        return customerTypeDTOS;
    }

    @Override
    public CustomerTypeDTO save(CustomerTypeDTO customerTypeDTO) {
        CustomerType customerType = repository.save(modelMapper().map(customerTypeDTO, CustomerType.class));
        return modelMapper().map(customerType, CustomerTypeDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CustomerTypeDTO customerTypeDTO) {
        repository.delete(modelMapper().map(customerTypeDTO, CustomerType.class));
    }

}
