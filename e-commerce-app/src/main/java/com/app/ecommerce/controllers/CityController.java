package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityController {
    private static final String URI="/cities";
    private static final String LIST_VIEW="cities";
    private static final String VIEW="add_city";

    @GetMapping(URI)
    public String getAllCities(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getCity(@PathVariable(name = "id")Long id, Model model){
        return VIEW;
    }
}
