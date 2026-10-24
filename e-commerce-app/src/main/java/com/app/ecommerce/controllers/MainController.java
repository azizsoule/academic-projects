package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final String URI="";
    private static final String VIEW="index";

    @Autowired
    private ArticleService service;

    @GetMapping(URI)
    public String index(){
        return VIEW;
    }
}
