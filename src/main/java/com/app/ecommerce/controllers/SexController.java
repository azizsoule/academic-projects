package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Sex;
import com.app.ecommerce.services.SexService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SexController {
    private static final String URI="/sexes";
    private static final String ADD_URI="/add-sex";
    private static final String LIST_VIEW="sex";
    private static final String VIEW="add_sex";

    @Autowired
    private SexService service;

    @GetMapping(URI)
    public String getAllSexs(Model model){
        model.addAttribute("sexes",service.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getSex(Model model) {
        model.addAttribute("sex", new Sex());
        return VIEW;
    }

    @GetMapping(URI+"/{id}")
    public String getSex(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("sex", service.findById(id));
        return VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postSex(Sex sex, RedirectAttributes ra) {
        try {
            service.save(sex);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }
}
