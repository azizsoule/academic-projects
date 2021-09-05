package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {
    private static final String URI="/catalogs";
    private static final String VIEW="catalog";

    @GetMapping(URI)
    private String getCatalogs(){
        return VIEW;
    }
}
