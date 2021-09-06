package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    private static final String URI="/categories";
    private static final String ADD_URI=URI+"/add-category";
    private static final String LIST_VIEW="category";
    private static final String VIEW="add_category";

    @GetMapping(URI)
    private String getAllCategory(){
        return LIST_VIEW;
    }
    @GetMapping(ADD_URI)
    private String getCategory(Model model){
        model.addAttribute("categoryDTO", new CategoryDTO());
        return VIEW;
    }

}
