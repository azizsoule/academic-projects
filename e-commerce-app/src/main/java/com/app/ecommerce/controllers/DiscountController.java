package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Discount;
import com.app.ecommerce.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DiscountController implements BaseController<Discount, Long>{
    private static final String URI="/discounts";
    private static final String LIST_VIEW="offers";
    private static final String VIEW="add_offer";

    @Autowired
    DiscountService service;

    @GetMapping(URI)
    public String getAllDiscounts(Model model){
        model.addAttribute("discounts", service.findAll());
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getDiscount(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("discount", service.findById(id));
        return VIEW;
    }

    public String getAll(Model model) {
        return null;
    }

    public String getById(Long id, Model model) {
        return null;
    }

    public String add(Model model) {
        return null;
    }

    public String post(Discount obj, RedirectAttributes ra) {
        return null;
    }

    public String update(Discount obj, Long id, RedirectAttributes ra) {
        return null;
    }

    public String delete(Discount obj, Long id, RedirectAttributes ra) {
        return null;
    }
}
