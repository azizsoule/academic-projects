package com.app.ecommerce.dtos;

public class DiscountDTO {
    private Long idDiscount;
    private java.sql.Timestamp createdAt;
    private String description;
    private Float discountAmount;
    private Integer discountPercent;
    private Boolean enabled;
    private java.sql.Timestamp endDate;
    private String label;
    private Boolean percentage;
    private String promoCode;
    private java.sql.Timestamp startDate;

    public Long getIdDiscount() {
        return this.idDiscount;
    }

    public void setIdDiscount(Long idDiscount) {
        this.idDiscount = idDiscount;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public java.sql.Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.sql.Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Boolean percentage) {
        this.percentage = percentage;
    }

    public String getPromoCode() {
        return this.promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public java.sql.Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }
}
