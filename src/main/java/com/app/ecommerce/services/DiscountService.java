package com.app.ecommerce.services;

import com.app.ecommerce.dtos.DiscountDTO;
import com.app.ecommerce.models.Discount;
import com.app.ecommerce.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService extends BaseService<DiscountDTO, Long> {

    @Autowired
    DiscountRepository repository;

    @Override
    public DiscountDTO findById(Long aLong) {
        Discount discount = repository.getById(aLong);
        return modelMapper().map(discount, DiscountDTO.class);
    }

    @Override
    public List<DiscountDTO> findAll() {
        List<DiscountDTO> discountDTOS = new ArrayList<>();
        List<Discount> discounts = repository.findAll();
        discounts.forEach(discount -> {
            discountDTOS.add(modelMapper().map(discount, DiscountDTO.class));
        });
        return discountDTOS;
    }

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        Discount discount = repository.save(modelMapper().map(discountDTO, Discount.class));
        return modelMapper().map(discount, DiscountDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(DiscountDTO discountDTO) {
        repository.delete(modelMapper().map(discountDTO, Discount.class));
    }

}
