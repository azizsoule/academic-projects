package com.app.ecommerce.services;

import com.app.ecommerce.models.CustomerPaymentMethod;
import com.app.ecommerce.repositories.CustomerPaymentMethodRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerPaymentMethodService extends BaseService<CustomerPaymentMethod, Long> {

    @Autowired
    CustomerPaymentMethodRepository repository;

    @Override
    public CustomerPaymentMethod findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<CustomerPaymentMethod> findAll() {
        return repository.findAll();
    }

    @Override
    public CustomerPaymentMethod save(CustomerPaymentMethod userPaymentMethod) {
        return repository.save(userPaymentMethod);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CustomerPaymentMethod customerPaymentMethod) {
        repository.delete(customerPaymentMethod);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final CustomerPaymentMethod customerPaymentMethod = this.modelMapper().map(data, CustomerPaymentMethod.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(customerPaymentMethod.getIdCustomerPayment());
        } else {
            save(customerPaymentMethod);
        }
    }

}
