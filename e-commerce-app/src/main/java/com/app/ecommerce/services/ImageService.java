package com.app.ecommerce.services;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.Image;
import com.app.ecommerce.repositories.ImageRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> imageData, Envelope.Operation operation) {
        final Image image = this.modelMapper().map(imageData, Image.class);
        if (Envelope.Operation.DELETE == operation) {
            this.deleteById(image.getId());
        } else {
            this.save(image);
        }
    }

}
