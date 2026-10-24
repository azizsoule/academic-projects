package com.app.ecommerce.controllers;

import com.app.ecommerce.models.OrderState;
import com.app.ecommerce.services.OrderStateService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderStateController {
    private static final String URI="/order-states";
    private static final String ADD_URI = "/add-order-state";
    private static final String LIST_VIEW="order_states";
    private static final String VIEW="add_order-state";

    @Autowired
    private OrderStateService service;

    @GetMapping(URI)
    public String getAllOrderStates(Model model){
        model.addAttribute("orderStates",service.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getOrderState(Model model) {
        model.addAttribute("orderState", new OrderState());
        return VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getOrderState(@PathVariable(name = "id")String id, Model model){
        model.addAttribute("OrderState", service.findById(id));
        return VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postOrderState(OrderState OrderState, @RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        try {
            service.save(OrderState);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }
}
