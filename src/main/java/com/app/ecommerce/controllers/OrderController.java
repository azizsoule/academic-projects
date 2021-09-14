package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Order;
import com.app.ecommerce.services.OrderService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class OrderController {
    private static final String URI = "/orders";
    private static final String ADD_URI = "/add-order";
    private static final String LIST_VIEW = "orders";
    private static final String VIEW = "order_view";
    private static final String EDIT_VIEW = "edit_order";


    @Autowired
    private OrderService orderService;

    @GetMapping(URI)
    private String getOrders(Model model) {
        model.addAttribute("orders",orderService.findAll());
        return LIST_VIEW;

    }

    @GetMapping(ADD_URI)
    private String getOrder(Model model){
        model.addAttribute("Order",new Order());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getOrderById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return VIEW;
    }



     @PostMapping(ADD_URI + "/save")
    private String postOrder(Order Order, RedirectAttributes ra) {
        try {
            orderService.save(Order);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }


    @PostMapping(URI + "/{id}/update")
    private String updateOrder(@PathVariable("id") Long id, Order Order,
                                 RedirectAttributes ra) {
        try {
            orderService.update(Order);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteOrder(@PathVariable("id") Long id, Order Order, RedirectAttributes ra) {
        try {
            orderService.delete(Order);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
