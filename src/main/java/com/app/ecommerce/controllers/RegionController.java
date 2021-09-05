package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RegionController {
    private static final String URI="/regions";
    private static final String LIST_VIEW="offers";
    private static final String VIEW="add_offer";

    @GetMapping(URI)
    public String getAllRegions(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getRegion(@PathVariable(name = "id")Long id, Model model){
        return VIEW;
    }
}
