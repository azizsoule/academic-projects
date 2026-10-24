package com.app.ecommerce.dtos;

public class ArticleDTO {
    private Long idArticle;
    private String brand;
    private Integer commentCount;
    private String description;
    private String image;
    private String label;
    private Float price;
    private Integer ratingSum;
    private String sku;
    private InventoryDTO inventoryDTO;
    private SubCategoryDTO subCategoryDTO;

    public Long getIdArticle() {
        return this.idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRatingSum() {
        return this.ratingSum;
    }

    public void setRatingSum(Integer ratingSum) {
        this.ratingSum = ratingSum;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public InventoryDTO getInventoryDTO() {
        return inventoryDTO;
    }

    public void setInventoryDTO(InventoryDTO inventoryDTO) {
        this.inventoryDTO = inventoryDTO;
    }

    public SubCategoryDTO getSubCategoryDTO() {
        return subCategoryDTO;
    }

    public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
        this.subCategoryDTO = subCategoryDTO;
    }
}
