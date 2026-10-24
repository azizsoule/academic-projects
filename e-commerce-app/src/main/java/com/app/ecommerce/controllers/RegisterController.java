package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    private static final String URI="/register";
    private static final String VIEW="register";

    @GetMapping(URI)
    private String getRegister(){
        return VIEW;
    }
}
