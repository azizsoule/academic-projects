package com.app.ecommerce.controllers;

import com.app.ecommerce.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.app.ecommerce.dtos.PaymentMethodDTO;
import com.app.ecommerce.dtos.CategoryDTO;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.utils.Router;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaymentSettingController {
    private static final String URI="/payment-setting";
    private static final String LIST_VIEW="payment_setting";
    private static final String VIEW="payment_setting_stripe";

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping(URI)
    private String getAllPayments(Model model) {
        model.addAttribute("payments", paymentMethodService.findAll());
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getPayment(@PathVariable(name = "id")Long id, Model model){

        return VIEW;
    }
}
