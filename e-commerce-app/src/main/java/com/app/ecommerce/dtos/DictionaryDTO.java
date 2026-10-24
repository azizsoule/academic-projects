package com.app.ecommerce.dtos;

public class DictionaryDTO {
    private Long idDictionary;
    private String label;

    public Long getIdDictionary() {
        return this.idDictionary;
    }

    public void setIdDictionary(Long idDictionary) {
        this.idDictionary = idDictionary;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
