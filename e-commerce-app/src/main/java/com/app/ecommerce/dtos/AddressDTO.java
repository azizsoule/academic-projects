package com.app.ecommerce.dtos;

public class AddressDTO {
    private Long idAddress;
    private Boolean def;
    private String firstName;
    private String label;
    private String lastName;
    private Long cityIdCity;
    private Long customerId;

    public Long getIdAddress() {
        return this.idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Boolean getDef() {
        return this.def;
    }

    public void setDef(Boolean def) {
        this.def = def;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCityIdCity() {
        return this.cityIdCity;
    }

    public void setCityIdCity(Long cityIdCity) {
        this.cityIdCity = cityIdCity;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
