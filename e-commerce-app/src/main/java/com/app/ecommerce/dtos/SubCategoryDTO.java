package com.app.ecommerce.dtos;

public class SubCategoryDTO {
    private Long idSubCategory;
    private String label;
    private Long categoryIdCategory;

    public Long getIdSubCategory() {
        return this.idSubCategory;
    }

    public void setIdSubCategory(Long idSubCategory) {
        this.idSubCategory = idSubCategory;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCategoryIdCategory() {
        return this.categoryIdCategory;
    }

    public void setCategoryIdCategory(Long categoryIdCategory) {
        this.categoryIdCategory = categoryIdCategory;
    }
}
