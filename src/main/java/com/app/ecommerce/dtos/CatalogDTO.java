package com.app.ecommerce.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatalogDTO {
    private Long idCatalog;
    private String image;
    private String label;
}
