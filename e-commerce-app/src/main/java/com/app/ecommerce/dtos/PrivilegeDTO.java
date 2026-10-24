package com.app.ecommerce.dtos;

public class PrivilegeDTO {
    private Long idPrivilege;
    private String description;
    private String label;

    public Long getIdPrivilege() {
        return this.idPrivilege;
    }

    public void setIdPrivilege(Long idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
