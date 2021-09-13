package com.app.ecommerce.services;

import com.app.ecommerce.models.PaymentDetail;
import com.app.ecommerce.repositories.PaymentDetailRepository;
import io.debezium.data.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void replicateData(Map<String, Object> data, Envelope.Operation operation) {
        final PaymentDetail paymentDetail = this.modelMapper().map(data, PaymentDetail.class);
        if (Envelope.Operation.DELETE == operation) {
            deleteById(paymentDetail.getIdPaymentDetail());
        } else {
            save(paymentDetail);
        }
    }

}
