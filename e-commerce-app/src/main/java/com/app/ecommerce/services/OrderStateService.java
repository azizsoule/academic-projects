package com.app.ecommerce.services;

import com.app.ecommerce.dtos.OrderStateDTO;
import com.app.ecommerce.models.OrderState;
import com.app.ecommerce.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStateService extends BaseService<OrderStateDTO, String> {

    @Autowired
    OrderStateRepository repository;

    @Override
    public OrderStateDTO findById(String s) {
        OrderState orderState = repository.getById(s);
        return modelMapper().map(orderState, OrderStateDTO.class);
    }

    @Override
    public List<OrderStateDTO> findAll() {
        List<OrderStateDTO> orderStateDTOS = new ArrayList<>();
        List<OrderState> orderStates = repository.findAll();
        orderStates.forEach(orderState -> {
            orderStateDTOS.add(modelMapper().map(orderState, OrderStateDTO.class));
        });
        return orderStateDTOS;
    }

    @Override
    public OrderStateDTO save(OrderStateDTO orderStateDTO) {
        OrderState orderState = repository.save(modelMapper().map(orderStateDTO, OrderState.class));
        return modelMapper().map(orderState, OrderStateDTO.class);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    @Override
    public void delete(OrderStateDTO orderStateDTO) {
        repository.delete(modelMapper().map(orderStateDTO, OrderState.class));
    }
}
