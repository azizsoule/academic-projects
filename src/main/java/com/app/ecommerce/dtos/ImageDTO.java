package com.app.ecommerce.dtos;

import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String image;
    private Long articleIdArticle;
}
