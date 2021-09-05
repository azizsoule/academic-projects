package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private static final String URI="/orders";
    private static final String VIEW="orders";

    @GetMapping(URI)
    private String getOrders(){
        return VIEW;
    }
}
