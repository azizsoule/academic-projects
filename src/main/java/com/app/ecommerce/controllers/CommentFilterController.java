package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentFilterController {
    private static final String URI="/comment-filter";
    private static final String VIEW="dictionnary";

    @GetMapping(URI)
    private String getWord(){
        return VIEW;
    }
}
