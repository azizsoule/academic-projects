package com.app.ecommerce.services;

import com.app.ecommerce.dtos.WishItemDTO;
import com.app.ecommerce.models.WishItem;
import com.app.ecommerce.repositories.WishItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishItemService extends BaseService<WishItemDTO, Long> {

    @Autowired
    WishItemRepository repository;

    @Override
    public WishItemDTO findById(Long aLong) {
        WishItem wishItem = repository.getById(aLong);
        return modelMapper().map(wishItem, WishItemDTO.class);
    }

    @Override
    public List<WishItemDTO> findAll() {
        List<WishItemDTO> wishItemDTOS = new ArrayList<>();
        List<WishItem> wishItems = repository.findAll();
        wishItems.forEach(wishItem -> {
            wishItemDTOS.add(modelMapper().map(wishItem, WishItemDTO.class));
        });
        return wishItemDTOS;
    }

    @Override
    public WishItemDTO save(WishItemDTO wishItemDTO) {
        WishItem wishItem = repository.save(modelMapper().map(wishItemDTO, WishItem.class));
        return modelMapper().map(wishItem, WishItemDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(WishItemDTO wishItemDTO) {
        repository.delete(modelMapper().map(wishItemDTO, WishItem.class));
    }

}
