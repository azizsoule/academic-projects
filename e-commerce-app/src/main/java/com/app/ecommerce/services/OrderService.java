package com.app.ecommerce.services;

import com.app.ecommerce.models.Order;
import com.app.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends BaseService<Order, Long> {

    @Autowired
    OrderRepository repository;

    @Override
    public Order findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Order order) {
        repository.delete(order);
    }

}
