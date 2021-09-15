package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Region;
import com.app.ecommerce.services.RegionService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegionController {
    private static final String URI="/regions";
    private static final String ADD_URI = "/add-region";
    private static final String LIST_VIEW="region";
    private static final String VIEW="add_region";
    private static final String EDIT_VIEW="edit_region";

    @Autowired
    private RegionService regionService;

    @GetMapping(URI)
    public String getAllRegions(Model model){
        model.addAttribute("regions",regionService.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getRegion(Model model) {
        model.addAttribute("region", new Region());
        return VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getRegion(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("region", regionService.findById(id));
        return EDIT_VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postRegion(Region region, RedirectAttributes ra) {
        try {
            regionService.save(region);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }
    @PostMapping(URI + "/{id}/update")
    private String updateRegion(@PathVariable("id") Long id, Region region,
                                  RedirectAttributes ra) {
        try {
            regionService.save(region);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }
}
