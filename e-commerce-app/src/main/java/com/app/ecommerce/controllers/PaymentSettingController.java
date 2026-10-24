package com.app.ecommerce.controllers;

import com.app.ecommerce.models.PaymentMethod;
import com.app.ecommerce.models.PaymentMethod;
import com.app.ecommerce.services.PaymentMethodService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaymentSettingController {
    private static final String URI="/payment-methods";
    private static final String ADD_URI="/add-new-payment";
    private static final String LIST_VIEW="payment_method";
    private static final String VIEW="payment_setting_stripe";

    @Autowired
    private PaymentMethodService service;

    @GetMapping(URI)
    public String getAllPaymentMethods(Model model){
        model.addAttribute("paymentMethods",service.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getPayment(Model model) {
        model.addAttribute("paymentMethod", new PaymentMethod());
        return VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getPaymentById(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("paymentMethod", service.findById(id));
        return VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postPayment(PaymentMethod paymentMethod, RedirectAttributes ra) {
        try {
            service.save(paymentMethod);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }
}
