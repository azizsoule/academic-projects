package com.app.ecommerce.dtos;

public class RegionDTO {
    private Long idRegion;
    private String label;

    public Long getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
