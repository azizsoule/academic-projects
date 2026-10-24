package com.app.ecommerce.services;

import com.app.ecommerce.dtos.PaymentMethodDTO;
import com.app.ecommerce.models.PaymentMethod;
import com.app.ecommerce.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodService extends BaseService<PaymentMethodDTO, Long> {

    @Autowired
    PaymentMethodRepository repository;

    @Override
    public PaymentMethodDTO findById(Long aLong) {
        PaymentMethod paymentMethod = repository.getById(aLong);
        return modelMapper().map(paymentMethod, PaymentMethodDTO.class);
    }

    @Override
    public List<PaymentMethodDTO> findAll() {
        List<PaymentMethodDTO> paymentMethodDTOS = new ArrayList<>();
        List<PaymentMethod> paymentMethods = repository.findAll();
        paymentMethods.forEach(paymentMethod -> {
            paymentMethodDTOS.add(modelMapper().map(paymentMethod, PaymentMethodDTO.class));
        });
        return paymentMethodDTOS;
    }

    @Override
    public PaymentMethodDTO save(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = repository.save(modelMapper().map(paymentMethodDTO, PaymentMethod.class));
        return modelMapper().map(paymentMethod, PaymentMethodDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(PaymentMethodDTO paymentMethodDTO) {
        repository.delete(modelMapper().map(paymentMethodDTO, PaymentMethod.class));
    }

}
