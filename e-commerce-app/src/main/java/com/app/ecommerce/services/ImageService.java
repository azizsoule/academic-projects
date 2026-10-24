package com.app.ecommerce.services;

import com.app.ecommerce.models.Image;
import com.app.ecommerce.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService extends BaseService<Image, Long> {

    @Autowired
    ImageRepository repository;

    @Override
    public Image findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(Image image) {
        repository.delete(image);
    }

}
