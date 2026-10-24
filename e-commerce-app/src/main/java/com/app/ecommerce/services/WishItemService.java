package com.app.ecommerce.services;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.models.WishItem;
import com.app.ecommerce.repositories.WishItemRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(WishItem wishItem) {
        repository.delete(wishItem);
    }

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final WishItem wishItem = this.modelMapper().map(data, WishItem.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(wishItem.getIdItem());
        } else {
            this.save(wishItem);
        }
    }

}
