package com.app.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {
    private static final String URI="/reports";
    private static final String LIST_VIEW="reports";
    private static final String VIEW="";

    @GetMapping(URI)
    public String getAllReports(Model model){
        return LIST_VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getReport(@PathVariable(name = "id")Long id, Model model){
        return VIEW;
    }
}
