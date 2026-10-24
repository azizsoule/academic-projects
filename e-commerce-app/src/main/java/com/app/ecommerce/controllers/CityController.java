package com.app.ecommerce.controllers;

import com.app.ecommerce.models.City;
import com.app.ecommerce.models.City;
import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.services.CityService;
import com.app.ecommerce.services.RegionService;
import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.MediaSaver;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

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
    private String getAllCity(Model model) {
        model.addAttribute("cities", service.findAll());
        return LIST_VIEW;
    }


    @GetMapping(ADD_URI)
    private String addCity(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("regions", regionService.findAll());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getCityById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("city", service.findById(id));
        model.addAttribute("regions", regionService.findAll());
        return EDIT_VIEW;
    }

    @PostMapping(URI + "/save")
    private String postCity(City city, RedirectAttributes ra) {
        try {
            
            service.save(city);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @PostMapping(URI + "/{id}/update")
    private String updateCity(@PathVariable("id") Long id, City city,
                                  RedirectAttributes ra) {
        try {
            service.update(city);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, City city, RedirectAttributes ra) {
        try {
            service.delete(city);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
