package com.app.ecommerce.dtos;

public class UserGroupDTO {
    private Long idUserGroup;
    private java.sql.Timestamp createdAt;
    private String label;

    public Long getIdUserGroup() {
        return this.idUserGroup;
    }

    public void setIdUserGroup(Long idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
