package com.app.ecommerce.dtos;

public class DiscountArticlesDTO {
    private Long discountsIdDiscount;
    private Long articlesIdArticle;

    public Long getDiscountsIdDiscount() {
        return this.discountsIdDiscount;
    }

    public void setDiscountsIdDiscount(Long discountsIdDiscount) {
        this.discountsIdDiscount = discountsIdDiscount;
    }

    public Long getArticlesIdArticle() {
        return this.articlesIdArticle;
    }

    public void setArticlesIdArticle(Long articlesIdArticle) {
        this.articlesIdArticle = articlesIdArticle;
    }
}
