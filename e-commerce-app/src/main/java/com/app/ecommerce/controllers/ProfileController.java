package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    private static final String URI="/profile";
    private static final String VIEW="edit_profile";

    @GetMapping(URI)
    public String getProfile(Model model){
        return VIEW;
    }
}
