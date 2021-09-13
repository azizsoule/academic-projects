package com.app.ecommerce.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long idCategory;
    private String image;
    private String label;
    private Long catalogIdCatalog;
}
