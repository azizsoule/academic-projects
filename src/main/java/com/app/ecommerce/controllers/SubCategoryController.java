package com.app.ecommerce.controllers;

import com.app.ecommerce.models.SubCategory;
import com.app.ecommerce.services.CategoryService;
import com.app.ecommerce.services.SubCategoryService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SubCategoryController {
    private static final String URI = "/sub-categories";
    private static final String ADD_URI = URI + "/new";
    private static final String LIST_VIEW = "sub_category";
    private static final String VIEW = "add_sub_category";
    private static final String EDIT_VIEW = "edit_sub_category";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping(URI)
    private String getAllSubCategory(Model model) {
        model.addAttribute("subCategories", subCategoryService.findAll());
        return LIST_VIEW;
    }


    @GetMapping(ADD_URI)
    private String getSubCategory(Model model) {
        model.addAttribute("subCategory", new SubCategory());
        model.addAttribute("categories", categoryService.findAll());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getSubCategoryById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("subCategory", subCategoryService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return EDIT_VIEW;
    }

    @PostMapping(URI + "/save")
    private String postCategory(SubCategory dto, RedirectAttributes ra) {
        try {
            subCategoryService.save(dto);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @PostMapping(URI + "/{id}/update")
    private String updateSubCategory(@PathVariable("id") Long id, SubCategory dto,
                                  RedirectAttributes ra) {
        try {
            subCategoryService.update(dto);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteSubCategory(@PathVariable("id") Long id, SubCategory dto, RedirectAttributes ra) {
        try {
            subCategoryService.delete(dto);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
