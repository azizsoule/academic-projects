package com.app.ecommerce.dtos;

public class CityDTO {
    private Long idCity;
    private String label;
    private Long regionIdRegion;

    public Long getIdCity() {
        return this.idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getRegionIdRegion() {
        return this.regionIdRegion;
    }

    public void setRegionIdRegion(Long regionIdRegion) {
        this.regionIdRegion = regionIdRegion;
    }
}
