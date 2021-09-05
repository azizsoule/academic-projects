package com.app.ecommerce.dtos;

public class CustomerDTO {
    private Long id;
    private java.sql.Timestamp birthDate;
    private Boolean blocked;
    private java.sql.Timestamp createdAt;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private Long customerTypeIdCustomerType;
    private Long sexIdSex;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Timestamp getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(java.sql.Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getBlocked() {
        return this.blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCustomerTypeIdCustomerType() {
        return this.customerTypeIdCustomerType;
    }

    public void setCustomerTypeIdCustomerType(Long customerTypeIdCustomerType) {
        this.customerTypeIdCustomerType = customerTypeIdCustomerType;
    }

    public Long getSexIdSex() {
        return this.sexIdSex;
    }

    public void setSexIdSex(Long sexIdSex) {
        this.sexIdSex = sexIdSex;
    }
}
