package com.app.ecommerce.dtos;

public class PubDTO {
    private Long idPub;
    private Boolean forSlider;
    private String image;
    private String label;

    public Long getIdPub() {
        return this.idPub;
    }

    public void setIdPub(Long idPub) {
        this.idPub = idPub;
    }

    public Boolean getForSlider() {
        return this.forSlider;
    }

    public void setForSlider(Boolean forSlider) {
        this.forSlider = forSlider;
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
}
