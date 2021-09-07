package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.CatalogDTO;
import com.app.ecommerce.services.CustomerService;
import com.app.ecommerce.services.PaymentDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.ecommerce.dtos.OrderDetailDTO;
import com.app.ecommerce.services.OrderService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class OrderController {
    private static final String URI = "/orders";
    private static final String ADD_URI = "/add-order";
    private static final String LIST_VIEW = "orders";
    private static final String LIST_CUSTOMMER = "customer";
    private static final String VIEW = "add_order";
    private static final String EDIT_VIEW = "edit_order";


    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentDetailService paymentDetailService;


    @GetMapping(URI)
    private String getOrders(Model model) {
        model.addAttribute("orders",customerService.findAll());
        return LIST_VIEW;

    }

    @GetMapping(ADD_URI)
    private String getOrder(Model model){
        model.addAttribute("orderDetailDTO",new OrderDetailDTO());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getOrderById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderDetailDTO", orderService.findById(id));
        return EDIT_VIEW;
    }

     @PostMapping(ADD_URI + "/save")
    private String postOrder(OrderDetailDTO orderDetailDTO, RedirectAttributes ra) {
        try {
            orderService.save(orderDetailDTO);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }


    @PostMapping(URI + "/{id}/update")
    private String updateOrder(@PathVariable("id") Long id, OrderDetailDTO orderDetailDTO,
                                 RedirectAttributes ra) {
        try {
            orderService.update(orderDetailDTO);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteOrder(@PathVariable("id") Long id, OrderDetailDTO orderDetailDTO, RedirectAttributes ra) {
        try {
            orderService.delete(orderDetailDTO);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
