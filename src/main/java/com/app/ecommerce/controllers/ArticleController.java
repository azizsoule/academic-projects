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
    private static final String ADD_URI="/add-article";
    private static final String LIST_VIEW="articles";
    private static final String VIEW="article_view";
    private static final String EDIT_VIEW="edit_article";
    private static final String ADD_VIEW="add_article";
    @Autowired
    private ArticleService service;

    @GetMapping(URI)
    public String getArticles(Model model){
        return LIST_VIEW;
    }
    @GetMapping(ADD_URI)
    public String addArticle(Model model){
        return ADD_VIEW;
    }
    @GetMapping(URI+"/{id}")
    public String getArticle(@PathVariable(name = "id") Long idArticle, Model model){
        return EDIT_VIEW;
    }

}
