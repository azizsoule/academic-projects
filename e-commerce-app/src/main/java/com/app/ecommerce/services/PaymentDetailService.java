package com.app.ecommerce.services;

import com.app.ecommerce.models.PaymentDetail;
import com.app.ecommerce.repositories.PaymentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailService extends BaseService<PaymentDetail, Long> {

    @Autowired
    PaymentDetailRepository repository;

    @Override
    public PaymentDetail findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<PaymentDetail> findAll() {
        return repository.findAll();
    }

    @Override
    public PaymentDetail save(PaymentDetail paymentDetail) {
        return repository.save(paymentDetail);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(PaymentDetail paymentDetail) {
        repository.delete(paymentDetail);
    }

}
