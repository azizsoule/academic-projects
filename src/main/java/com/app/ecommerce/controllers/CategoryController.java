package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Image;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.services.CategoryService;
import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.MediaSaver;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class CategoryController {
    private static final String URI = "/categories";
    private static final String ADD_URI = URI + "/new";
    private static final String LIST_VIEW = "category";
    private static final String VIEW = "add_category";
    private static final String EDIT_VIEW = "edit_category";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CatalogService catalogService;

    @GetMapping(URI)
    private String getAllCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return LIST_VIEW;
    }


    @GetMapping(ADD_URI)
    private String addCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("catalogs", catalogService.findAll());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getCategoryById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("catalogs", catalogService.findAll());
        return EDIT_VIEW;
    }

    @PostMapping(URI + "/save")
    private String postCategory(@RequestParam("file") MultipartFile multipartFile, Category category, RedirectAttributes ra) {
        try {
            if(!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                category.setImage(fileName);
                categoryService.save(category);
                String uploadDir = Constants.CATEGORIES_MEDIA_DIR+'/';
                MediaSaver.saveFile(uploadDir, fileName, multipartFile);
            }
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @PostMapping(URI + "/{id}/update")
    private String updateCategory(@PathVariable("id") Long id, Category category,
                                  RedirectAttributes ra) {
        try {
            categoryService.update(category);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, Category category, RedirectAttributes ra) {
        try {
            categoryService.delete(category);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }

}
