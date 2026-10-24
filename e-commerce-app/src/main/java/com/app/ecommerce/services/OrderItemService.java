package com.app.ecommerce.services;

import com.app.ecommerce.dtos.OrderItemDTO;
import com.app.ecommerce.models.OrderItem;
import com.app.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService extends BaseService<OrderItemDTO, Long> {

    @Autowired
    OrderItemRepository repository;

    @Override
    public OrderItemDTO findById(Long aLong) {
        OrderItem orderItem = repository.getById(aLong);
        return modelMapper().map(orderItem, OrderItemDTO.class);
    }

    @Override
    public List<OrderItemDTO> findAll() {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        List<OrderItem> orderItems = repository.findAll();
        orderItems.forEach(orderItem -> {
            orderItemDTOS.add(modelMapper().map(orderItem, OrderItemDTO.class));
        });
        return orderItemDTOS;
    }

    @Override
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = repository.save(modelMapper().map(orderItemDTO, OrderItem.class));
        return modelMapper().map(orderItem, OrderItemDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(OrderItemDTO orderItemDTO) {
        repository.delete(modelMapper().map(orderItemDTO, OrderItem.class));
    }

}
