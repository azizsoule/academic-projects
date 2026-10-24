package com.app.ecommerce.dtos;

public class CartItemDTO {
    private Long idItem;
    private java.sql.Timestamp createdAt;
    private Integer quantity;
    private Long articleIdArticle;
    private Long customerId;

    public Long getIdItem() {
        return this.idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getArticleIdArticle() {
        return this.articleIdArticle;
    }

    public void setArticleIdArticle(Long articleIdArticle) {
        this.articleIdArticle = articleIdArticle;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
