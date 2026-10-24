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

import java.util.*;
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

    @Autowired
    CommentService commentService;

    @GetMapping(Route.SUB_CATEGORY+"/{id}")
    String subCategory(@AuthenticationPrincipal Customer customer, @PathVariable String id, Model model) {
        model.addAttribute("customer", customer);
        model.addAttribute("subCategory", subCategoryService.findById(Long.parseLong(id)));
        return Route.SUB_CATEGORY;
    }

    @GetMapping(Route.PRODUCT+"/{id}")
    String article(@AuthenticationPrincipal Customer customer, @PathVariable String id, Model model) {
        Article article = articleService.findById(Long.parseLong(id));
        AtomicBoolean hasAlreadyOrderedProduct = new AtomicBoolean(false);
        if (customer != null) {
            customer = customerService.findById(customer.getId());
            customer.getOrders().forEach(order -> {
                if (order.getOrderState().getCodeOrderState().equals("COMPLETE")) {
                    order.getOrderItems().forEach(orderItem -> {
                        if (orderItem.getArticle().getIdArticle() == article.getIdArticle()) {
                            hasAlreadyOrderedProduct.set(true);
                        }
                    });
                }
            });
        }

        List<Article> recommendedList = new ArrayList<>();

        var counter = new Object() {
            int i = 0;
        };

        article.getSubCategory().getArticles().forEach(art -> {
            if (art.getComments().size()!=0 && art.getRatingSum()!=0) {
                if (art.getRatingSum() / art.getComments().size() >= 3 && counter.i <= 5) {
                    recommendedList.add(article);
                    counter.i++;
                }
            }
        });

        /*
        List<Article> articles = new ArrayList<>(article.getSubCategory().getArticles());
        articles.sort(Comparator.comparing(a -> (a.getRatingSum() / (a.getComments().size() == 0 ? 1 : a.getComments().size()))));

        if (articles.size() >= 5) {

        }*/

        model.addAttribute("recommendedList", recommendedList);
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
    public String addCommentToArticle(@AuthenticationPrincipal Customer customer, @PathVariable Long articleId, Comment comment) {
        customer = customerService.findById(customer.getId());
        Article article = articleService.findById(articleId);
        comment.setArticle(article);
        comment.setCustomer(customer);
        commentService.save(comment);
        article.setRatingSum(article.getRatingSum()+comment.getRating());
        articleService.update(article);
        return Route.redirectTo(Route.PRODUCT+"/"+articleId);
    }

}
