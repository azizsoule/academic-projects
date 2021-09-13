package com.app.ecommerce.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CatalogDTO {
    private Long idCatalog;
    private String image;
    private String label;
    private List<CategoryDTO> categories = new ArrayList<>();
}
