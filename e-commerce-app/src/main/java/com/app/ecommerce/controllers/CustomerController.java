package com.app.ecommerce.controllers;

import com.app.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    private static final String URI="/customers";
    private static final String LIST_VIEW="customers";
    private static final String EDIT_VIEW="edit_customer";
    private static final String VIEW="customer_view";

    @Autowired
    CustomerService customerService;

    @GetMapping(URI)
    public String getAllCustomers(Model model){
        model.addAttribute("customers",customerService.findAll());
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}/view")
    public String getCustomer(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return VIEW;
    }


}
