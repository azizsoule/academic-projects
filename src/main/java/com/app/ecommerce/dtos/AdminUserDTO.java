package com.app.ecommerce.dtos;

public class AdminUserDTO {
    private Long id;
    private java.sql.Timestamp birthDate;
    private Boolean blocked;
    private java.sql.Timestamp createdAt;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private Boolean passwordExpired;
    private String username;
    private Long sexIdSex;
    private Long idUserGroup;

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

    public Boolean getPasswordExpired() {
        return this.passwordExpired;
    }

    public void setPasswordExpired(Boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSexIdSex() {
        return this.sexIdSex;
    }

    public void setSexIdSex(Long sexIdSex) {
        this.sexIdSex = sexIdSex;
    }

    public Long getIdUserGroup() {
        return this.idUserGroup;
    }

    public void setIdUserGroup(Long idUserGroup) {
        this.idUserGroup = idUserGroup;
    }
}
