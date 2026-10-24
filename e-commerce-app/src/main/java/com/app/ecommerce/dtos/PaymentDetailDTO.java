package com.app.ecommerce.dtos;

public class PaymentDetailDTO {
    private Long idPaymentDetail;
    private Float amount;
    private java.sql.Timestamp createdAt;
    private String reference;
    private String status;
    private Long paymentMethodIdPaymentMethod;

    public Long getIdPaymentDetail() {
        return this.idPaymentDetail;
    }

    public void setIdPaymentDetail(Long idPaymentDetail) {
        this.idPaymentDetail = idPaymentDetail;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPaymentMethodIdPaymentMethod() {
        return this.paymentMethodIdPaymentMethod;
    }

    public void setPaymentMethodIdPaymentMethod(Long paymentMethodIdPaymentMethod) {
        this.paymentMethodIdPaymentMethod = paymentMethodIdPaymentMethod;
    }
}
