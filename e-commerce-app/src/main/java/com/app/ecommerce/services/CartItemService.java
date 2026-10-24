package com.app.ecommerce.services;

import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CartItem> findAllByCustomer(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return repository.save(cartItem);
    }

    public CartItem addToCart(CartItem cartItem) {
        if (repository.existsByCustomerAndArticle(cartItem.getCustomer(), cartItem.getArticle())) {
            CartItem cartItemDB = repository.findByCustomerAndArticle(cartItem.getCustomer(), cartItem.getArticle());
            cartItemDB.setQuantity(cartItemDB.getQuantity()+cartItem.getQuantity());
            cartItem = this.update(cartItemDB);
        } else {
            cartItem = this.save(cartItem);
        }
        return cartItem;
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CartItem cartItem) {
        repository.delete(cartItem);
    }

}
