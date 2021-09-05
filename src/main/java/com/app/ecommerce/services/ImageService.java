package com.app.ecommerce.services;

import com.app.ecommerce.dtos.ImageDTO;
import com.app.ecommerce.models.Image;
import com.app.ecommerce.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService extends BaseService<ImageDTO, Long> {

    @Autowired
    ImageRepository repository;

    @Override
    public ImageDTO findById(Long aLong) {
        Image image = repository.getById(aLong);
        return modelMapper().map(image, ImageDTO.class);
    }

    @Override
    public List<ImageDTO> findAll() {
        List<ImageDTO> imageDTOS = new ArrayList<>();
        List<Image> images = repository.findAll();
        images.forEach(image -> {
            imageDTOS.add(modelMapper().map(image, ImageDTO.class));
        });
        return imageDTOS;
    }

    @Override
    public ImageDTO save(ImageDTO imageDTO) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(ImageDTO imageDTO) {
        repository.delete(modelMapper().map(imageDTO, Image.class));
    }

}
