package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubCategoryControllers {
    private static final String URI="/sub-categories";
    private static final String VIEW="category";

    @GetMapping(URI)
    private String getSubCategory(){
        return VIEW;
    }
}
