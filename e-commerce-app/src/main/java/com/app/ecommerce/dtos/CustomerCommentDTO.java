package com.app.ecommerce.dtos;

public class CustomerCommentDTO {
    private Long idComment;
    private Boolean blocked;
    private String content;
    private java.sql.Timestamp createdAt;
    private Integer rating;
    private Long idArticle;
    private Long customerId;

    public Long getIdComment() {
        return this.idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Boolean getBlocked() {
        return this.blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getIdArticle() {
        return this.idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
