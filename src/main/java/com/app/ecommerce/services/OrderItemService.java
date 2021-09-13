package com.app.ecommerce.services;

import com.app.ecommerce.models.OrderItem;
import com.app.ecommerce.repositories.OrderItemRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemService extends BaseService<OrderItem, Long> {

    @Autowired
    OrderItemRepository repository;

    @Override
    public OrderItem findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return  repository.save(orderItem);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(OrderItem orderItem) {
        repository.delete(orderItem);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final OrderItem orderItem = this.modelMapper().map(data, OrderItem.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(orderItem.getIdItem());
        } else {
            save(orderItem);
        }
    }
}
