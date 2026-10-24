package com.app.ecommerce.services;

import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.CartItemRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartItemService extends BaseService<CartItem, Long> {

    @Autowired
    CartItemRepository repository;

    @Override
    public CartItem findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<CartItem> findAll() {
        return repository.findAll();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return repository.save(cartItem);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CartItem cartItem) {
        repository.delete(cartItem);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final CartItem cartItem = this.modelMapper().map(data, CartItem.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(cartItem.getIdItem());
        } else {
            save(cartItem);
        }
    }

}
