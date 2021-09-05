package com.app.ecommerce.dtos;

public class CustomerTypeDTO {
    private Long idCustomerType;
    private Integer end;
    private String label;
    private Integer start;

    public Long getIdCustomerType() {
        return this.idCustomerType;
    }

    public void setIdCustomerType(Long idCustomerType) {
        this.idCustomerType = idCustomerType;
    }

    public Integer getEnd() {
        return this.end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
