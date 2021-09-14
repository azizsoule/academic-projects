package com.app.ecommerce.services;

import com.app.ecommerce.dtos.OrderDetailDTO;
import com.app.ecommerce.models.Order;
import com.app.ecommerce.models.OrderItem;
import com.app.ecommerce.repositories.OrderRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        repository.delete(modelMapper().map(order, Order.class));
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final Order order = this.modelMapper().map(data, Order.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(order.getIdOrder());
        } else {
            save(order);
        }
    }

}
