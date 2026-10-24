package com.app.ecommerce.services;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.models.WishItem;
import com.app.ecommerce.repositories.WishItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishItemService extends BaseService<WishItem, Long> {

    @Autowired
    WishItemRepository repository;

    @Override
    public WishItem findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<WishItem> findAll() {
        return repository.findAll();
    }

    @Override
    public WishItem save(WishItem wishItem) {
        return repository.save(wishItem);
    }

    public WishItem add(WishItem wishItem) {
        if (!repository.existsByCustomerAndArticle(wishItem.getCustomer(), wishItem.getArticle())) {
            return this.save(wishItem);
        } else {
            return repository.findByCustomerAndArticle(wishItem.getCustomer(), wishItem.getArticle());
        }
    }

    public List<WishItem> findAllByCustomer(Customer customer) {
        return repository.findAllByCustomer(customer);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(WishItem wishItem) {
        repository.delete(wishItem);
    }

}
