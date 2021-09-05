package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private static final String URI="/categories";
    private static final String VIEW="category";

    @GetMapping(URI)
    private String getAllCategory(){
        return VIEW;
    }

}
