package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentSettingController {
    private static final String URI="/payment-setting";
    private static final String LIST_VIEW="payment_setting";
    private static final String VIEW="payment_setting_stripe";

    @GetMapping(URI)
    public String getAllPayements(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getPayment(@PathVariable(name = "id")Long id, Model model){
        return VIEW;
    }
}
