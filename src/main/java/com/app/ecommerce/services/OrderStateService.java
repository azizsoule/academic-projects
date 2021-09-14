package com.app.ecommerce.services;

import com.app.ecommerce.models.OrderState;
import com.app.ecommerce.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStateService extends BaseService<OrderState, String> {

    @Autowired
    OrderStateRepository repository;

    @Override
    public OrderState findById(String s) {
        return repository.getById(s);
    }

    @Override
    public List<OrderState> findAll() {
        return repository.findAll();
    }

    @Override
    public OrderState save(OrderState orderState) {
        return repository.save(orderState);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    @Override
    public void delete(OrderState orderState) {
        repository.delete(orderState);
    }
}
