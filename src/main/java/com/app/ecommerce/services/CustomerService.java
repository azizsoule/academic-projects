package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CustomerDTO;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends BaseService<CustomerDTO, Long> {

    @Autowired
    CustomerRepository repository;

    @Override
    public CustomerDTO findById(Long aLong) {
        Customer customer = repository.getById(aLong);
        return modelMapper().map(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers.forEach(customer -> {
            customerDTOS.add(modelMapper().map(customer, CustomerDTO.class));
        });
        return customerDTOS;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = repository.save(modelMapper().map(customerDTO, Customer.class));
        return modelMapper().map(customer, CustomerDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CustomerDTO customerDTO) {
        repository.delete(modelMapper().map(customerDTO, Customer.class));
    }

}
