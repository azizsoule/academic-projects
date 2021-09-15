package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Order;
import com.app.ecommerce.services.ArticleService;
import com.app.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private static final String URI="";
    private static final String VIEW="index";

    @Autowired
    private ArticleService service;
    @Autowired
    private OrderService orderService;

    @GetMapping(URI )
    public String index( Model model){
        List<Order> orders = orderService.findAll();
        List<Order> completeOrder = orders.stream().filter(order -> order.getOrderState().getCodeOrderState().equals("COMPLETE")).collect(Collectors.toList());
        int nbrCanceled= (int) orders.stream().filter(order -> order.getOrderState().getCodeOrderState().equals("CANCELED")).count();
        int nbrPending= (int) orders.stream().filter(order -> order.getOrderState().getCodeOrderState().equals("PENDING")).count();
        AtomicInteger nbrPurchased = new AtomicInteger();
        AtomicInteger totalAmount = new AtomicInteger();
        completeOrder.forEach(order -> {
            order.getOrderItems().forEach(orderItem -> nbrPurchased.addAndGet(orderItem.getQuantity()));
        });
        model.addAttribute("recentOrders", orderService.findRecentOrders());
        model.addAttribute("nbrPurchased", nbrPurchased.intValue());
        model.addAttribute("nbrCanceled", nbrCanceled);
        model.addAttribute("nbrComplete", completeOrder.size());
        model.addAttribute("nbrPending", nbrPending);
        return VIEW;
    }

}
