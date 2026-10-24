package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.models.Comment;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.services.*;
import com.app.ecommerce.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@RequestMapping("/")
public class ShopController extends BaseController {

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CustomerService customerService;

    @GetMapping(Route.SUB_CATEGORY+"/{id}")
    String subCategory(@AuthenticationPrincipal Customer customer, @PathVariable String id, Model model) {
        model.addAttribute("customer", customer);
        model.addAttribute("subCategory", subCategoryService.findById(Long.parseLong(id)));
        return Route.SUB_CATEGORY;
    }

    @GetMapping(Route.PRODUCT+"/{id}")
    String article(@AuthenticationPrincipal Customer customer, @PathVariable String id, Model model) {
        customer = customerService.findById(customer.getId());
        Article article = articleService.findById(Long.parseLong(id));
        AtomicBoolean hasAlreadyOrderedProduct = new AtomicBoolean(false);
        customer.getOrders().forEach(order -> {
            order.getOrderItems().forEach(orderItem -> {
                if (orderItem.getArticle().equals(article)) {
                    hasAlreadyOrderedProduct.set(true);
                }
            });
        });
        model.addAttribute("customer", customer);
        model.addAttribute("article", article);
        model.addAttribute("comment", new Comment());
        model.addAttribute("hasAlreadyOrderedProduct", hasAlreadyOrderedProduct.get());
        return Route.PRODUCT;
    }

    @GetMapping(Route.QUICK_VIEW+"/{id}")
    public String quickView(@AuthenticationPrincipal Customer customer, @PathVariable String id, Model model) {
        Article article = articleService.findById(Long.parseLong(id));
        model.addAttribute("customer", customer);
        model.addAttribute("article", article);
        return Route.QUICK_VIEW;
    }

    @PostMapping(Route.PRODUCT+"/{articleId}/comment/add")
    public String addCommentToArticle(@AuthenticationPrincipal Customer customer, @PathVariable String articleId, Comment comment) {
        customer = customerService.findById(customer.getId());
        Article article = articleService.findById(Long.parseLong(articleId));
        articleService.addComment(customer, article, comment);
        return Route.redirectTo(Route.PRODUCT+"/"+articleId);
    }

}
