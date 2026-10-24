package com.app.ecommerce.services;

import com.app.ecommerce.dtos.PaymentDetailDTO;
import com.app.ecommerce.models.PaymentDetail;
import com.app.ecommerce.repositories.PaymentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDetailService extends BaseService<PaymentDetailDTO, Long> {

    @Autowired
    PaymentDetailRepository repository;

    @Override
    public PaymentDetailDTO findById(Long aLong) {
        PaymentDetail paymentDetail = repository.getById(aLong);
        return modelMapper().map(paymentDetail, PaymentDetailDTO.class);
    }

    @Override
    public List<PaymentDetailDTO> findAll() {
        List<PaymentDetailDTO> paymentDetailDTOS = new ArrayList<>();
        List<PaymentDetail> paymentDetails = repository.findAll();
        paymentDetails.forEach(paymentDetail -> {
            paymentDetailDTOS.add(modelMapper().map(paymentDetail, PaymentDetailDTO.class));
        });
        return paymentDetailDTOS;
    }

    @Override
    public PaymentDetailDTO save(PaymentDetailDTO paymentDetailDTO) {
        PaymentDetail paymentDetail = repository.save(modelMapper().map(paymentDetailDTO, PaymentDetail.class));
        return modelMapper().map(paymentDetail, PaymentDetailDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(PaymentDetailDTO paymentDetailDTO) {
        repository.delete(modelMapper().map(paymentDetailDTO, PaymentDetail.class));
    }

}
