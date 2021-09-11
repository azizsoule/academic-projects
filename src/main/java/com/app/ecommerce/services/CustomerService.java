package com.app.ecommerce.services;

import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends BaseService<Customer, Long> {

    @Autowired
    CustomerRepository repository;

    @Override
    public Customer findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

}
