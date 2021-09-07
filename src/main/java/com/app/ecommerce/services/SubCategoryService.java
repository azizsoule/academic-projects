package com.app.ecommerce.services;

import com.app.ecommerce.models.SubCategory;
import com.app.ecommerce.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService extends BaseService<SubCategory, Long> {

    @Autowired
    SubCategoryRepository repository;

    @Override
    public SubCategory findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<SubCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        return repository.save(subCategory);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(SubCategory subCategory) {
        repository.delete(subCategory);
    }

}
