package com.app.ecommerce.services;

import com.app.ecommerce.dtos.CategoryDTO;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService extends BaseService<CategoryDTO, Long> {

    @Autowired
    CategoryRepository repository;

    @Override
    public CategoryDTO findById(Long aLong) {
        Category category = repository.getById(aLong);
        return modelMapper().map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<Category> categories = repository.findAll();
        categories.forEach(category -> {
            categoryDTOS.add(modelMapper().map(category, CategoryDTO.class));
        });
        return categoryDTOS;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = modelMapper().map(categoryDTO, Category.class);
        category = repository.save(category);
        return modelMapper().map(category, CategoryDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        repository.delete(modelMapper().map(categoryDTO, Category.class));
    }

}
