package com.app.ecommerce.dtos;

public class InventoryDTO {
    private Long idIventory;
    private Integer quantity;

    public Long getIdIventory() {
        return this.idIventory;
    }

    public void setIdIventory(Long idIventory) {
        this.idIventory = idIventory;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
