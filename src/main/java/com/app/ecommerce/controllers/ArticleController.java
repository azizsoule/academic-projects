package com.app.ecommerce.controllers;

import com.app.ecommerce.repositories.ArticleRepository;
import com.app.ecommerce.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
    private static final String URI="/articles";
    private static final String LIST_VIEW="home";
    private static final String VIEW="product";
    @Autowired
    private ArticleService service;
    @GetMapping(URI)
    public String getArticles(Model model){
        model.addAttribute("articles", service.findAll());
        return LIST_VIEW;
    }
    @GetMapping(URI+"/{id}")
    public String getArticle(@PathVariable(name = "id") Long idArticle, Model model){
        model.addAttribute("article", service.findById(idArticle));
        return VIEW;
    }

}
