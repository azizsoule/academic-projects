package com.app.ecommerce.dtos;

public class OrderDetailDTO {
    private Long idOrder;
    private java.sql.Timestamp createdAt;
    private Float total;
    private Long customerId;
    private String codeOrderState;
    private Long paymentDetailIdPaymentDetail;

    public Long getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Float getTotal() {
        return this.total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCodeOrderState() {
        return this.codeOrderState;
    }

    public void setCodeOrderState(String codeOrderState) {
        this.codeOrderState = codeOrderState;
    }

    public Long getPaymentDetailIdPaymentDetail() {
        return this.paymentDetailIdPaymentDetail;
    }

    public void setPaymentDetailIdPaymentDetail(Long paymentDetailIdPaymentDetail) {
        this.paymentDetailIdPaymentDetail = paymentDetailIdPaymentDetail;
    }
}
