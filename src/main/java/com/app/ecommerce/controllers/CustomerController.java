package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    private static final String URI="/customers";
    private static final String LIST_VIEW="customers";
    private static final String VIEW="";

    @GetMapping(URI)
    public String getAllCustomers(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getCustomer(@PathVariable(name = "id")Long id, Model model){
        return VIEW;
    }

}
