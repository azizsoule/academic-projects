package com.app.ecommerce.services;

import com.app.ecommerce.dtos.OrderDetailDTO;
import com.app.ecommerce.models.Order;
import com.app.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends BaseService<OrderDetailDTO, Long> {

    @Autowired
    OrderRepository repository;

    @Override
    public OrderDetailDTO findById(Long aLong) {
        Order order = repository.getById(aLong);
        return modelMapper().map(order, OrderDetailDTO.class);
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        List<Order> orders = repository.findAll();
        orders.forEach(order -> {
            orderDetailDTOS.add(modelMapper().map(order, OrderDetailDTO.class));
        });
        return orderDetailDTOS;
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
        Order order = repository.save(modelMapper().map(orderDetailDTO, Order.class));
        return modelMapper().map(order, OrderDetailDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(OrderDetailDTO orderDetailDTO) {
        repository.delete(modelMapper().map(orderDetailDTO, Order.class));
    }

}
