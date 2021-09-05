package com.app.ecommerce.dtos;

public class ImageDTO {
    private Long id;
    private String image;
    private Long articleIdArticle;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getArticleIdArticle() {
        return this.articleIdArticle;
    }

    public void setArticleIdArticle(Long articleIdArticle) {
        this.articleIdArticle = articleIdArticle;
    }
}
