package com.app.ecommerce.services;

import com.app.ecommerce.dtos.SubCategoryDTO;
import com.app.ecommerce.models.SubCategory;
import com.app.ecommerce.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryService extends BaseService<SubCategoryDTO, Long> {

    @Autowired
    SubCategoryRepository repository;

    @Override
    public SubCategoryDTO findById(Long aLong) {
        SubCategory subCategory = repository.getById(aLong);
        return modelMapper().map(subCategory, SubCategoryDTO.class);
    }

    @Override
    public List<SubCategoryDTO> findAll() {
        List<SubCategoryDTO> subCategoryDTOS = new ArrayList<>();
        List<SubCategory> subCategories = repository.findAll();
        subCategories.forEach(subCategory -> {
            subCategoryDTOS.add(modelMapper().map(subCategory, SubCategoryDTO.class));
        });
        return subCategoryDTOS;
    }

    @Override
    public SubCategoryDTO save(SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = repository.save(modelMapper().map(subCategoryDTO, SubCategory.class));
        return modelMapper().map(subCategory, SubCategoryDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(SubCategoryDTO subCategoryDTO) {
        repository.delete(modelMapper().map(subCategoryDTO, SubCategory.class));
    }

}
