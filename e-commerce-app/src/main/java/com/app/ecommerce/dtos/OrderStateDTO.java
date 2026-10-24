package com.app.ecommerce.dtos;

public class OrderStateDTO {
    private String codeOrderState;
    private String label;

    public String getCodeOrderState() {
        return this.codeOrderState;
    }

    public void setCodeOrderState(String codeOrderState) {
        this.codeOrderState = codeOrderState;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
