package com.app.ecommerce.services;

import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.CustomerRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Customer customer = this.modelMapper().map(data, Customer.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(customer.getId());
        } else {
            save(customer);
        }
    }

}
