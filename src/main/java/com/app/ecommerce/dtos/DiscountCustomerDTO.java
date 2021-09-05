package com.app.ecommerce.dtos;

public class DiscountCustomerDTO {
    private Long discountId;
    private Long customerId;

    public Long getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
