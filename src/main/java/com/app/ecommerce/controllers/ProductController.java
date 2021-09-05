package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    private static final String URI="/products";
    private static final String LIST_VIEW="products";
    private static final String VIEW="product_view";

    @GetMapping(URI)
    private String getAllProducts(){
        return LIST_VIEW;
    }

}
