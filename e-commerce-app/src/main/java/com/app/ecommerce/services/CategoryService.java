package com.app.ecommerce.services;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<Category, Long> {

    @Autowired
    CategoryRepository repository;

    @Override
    public Category findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Category category) {
        repository.delete(category);
    }

}
