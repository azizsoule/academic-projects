package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.SubCategory;
import com.app.ecommerce.repositories.SubCategoryRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final SubCategory subCategory = this.modelMapper().map(data, SubCategory.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(subCategory.getIdSubCategory());
        } else {
            this.save(subCategory);
        }
    }

}
