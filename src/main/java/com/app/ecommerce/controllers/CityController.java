package com.app.ecommerce.controllers;

import com.app.ecommerce.models.City;
import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.services.CityService;
import com.app.ecommerce.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityController {
    private static final String URI="/cities";
    private static final String LIST_VIEW="cities";
    private static final String ADD_URI = "/add-new";
    private static final String VIEW="add_city";
    private static final String EDIT_VIEW = "edit_city";

    @Autowired
    private CityService service;

    @Autowired
    private RegionService regionService;

    @GetMapping(URI)
    public String getAllCities(Model model){
        model.addAttribute("cities", service.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getUserGroup(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("regions",regionService.findAll());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getCity(@PathVariable("id") Long id, Model model) {
        model.addAttribute("city", service.findById(id));
        model.addAttribute("regions",regionService.findAll());
        return EDIT_VIEW;
    }
}
