package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CartItemDTO;
import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService extends BaseService<CartItemDTO, Long> {

    @Autowired
    CartItemRepository repository;

    @Override
    public CartItemDTO findById(Long aLong) {
        CartItem cartItem = repository.getById(aLong);
        return modelMapper().map(cartItem, CartItemDTO.class);
    }

    @Override
    public List<CartItemDTO> findAll() {
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        List<CartItem> cartItems = repository.findAll();
        cartItems.forEach(cartItem -> {
            cartItemDTOS.add(modelMapper().map(cartItem, CartItemDTO.class));
        });
        return cartItemDTOS;
    }

    @Override
    public CartItemDTO save(CartItemDTO cartItemDTO) {
        CartItem cartItem = modelMapper().map(cartItemDTO, CartItem.class);
        cartItem = repository.save(cartItem);
        return modelMapper().map(cartItem, CartItemDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CartItemDTO cartItemDTO) {
        CartItem cartItem = modelMapper().map(cartItemDTO, CartItem.class);
        repository.delete(cartItem);
    }

}
