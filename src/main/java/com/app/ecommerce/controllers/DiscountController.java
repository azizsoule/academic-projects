package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.DiscountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DiscountController implements BaseController<DiscountDTO, Long>{
    private static final String URI="/discounts";
    private static final String LIST_VIEW="offers";
    private static final String VIEW="add_offer";

    @GetMapping(URI)
    public String getAllDiscounts(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getDiscount(@PathVariable(name = "id")Long id, Model model){
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

    public String post(DiscountDTO obj, RedirectAttributes ra) {
        return null;
    }

    public String update(DiscountDTO obj, Long id, RedirectAttributes ra) {
        return null;
    }

    public String delete(DiscountDTO obj, Long id, RedirectAttributes ra) {
        return null;
    }
}
