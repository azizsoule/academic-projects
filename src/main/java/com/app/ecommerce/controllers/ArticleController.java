package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Article;
import com.app.ecommerce.services.ArticleService;
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
public class ArticleController implements BaseController<Article, Long> {
    private static final String URI = "/articles";
    private static final String ADD_URI = "/add-article";
    private static final String LIST_VIEW = "articles";
    private static final String VIEW = "article_view";
    private static final String EDIT_VIEW = "edit_article";
    private static final String ADD_VIEW = "add_article";
    @Autowired
    private ArticleService service;
    @Autowired
    private SubCategoryService subCategoryService;

    @Override
    @GetMapping(URI)
    public String getAll(Model model) {
        model.addAttribute("articles", service.findAll());
        return LIST_VIEW;
    }

    @Override
    @GetMapping(URI + "/{id}")
    public String getById(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("article", service.findById(id));
        model.addAttribute("subCategories", subCategoryService.findAll());
        return VIEW;
    }

    @GetMapping(URI + "/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("article", service.findById(id));
        model.addAttribute("subCategories", subCategoryService.findAll());
        return EDIT_VIEW;
    }

    @Override
    @GetMapping(ADD_URI)
    public String add(Model model) {
        Article article = new Article();
        article.setLabel("testArticle");
        article.setSku("SKUTEST");
        article.setBrand("testBrand");
        article.setPrice(250000);
        model.addAttribute("article", article);
        model.addAttribute("subCategories", subCategoryService.findAll());
        return ADD_VIEW;
    }

    @Override
    @PostMapping(URI + "/save")
    public String post(Article obj, RedirectAttributes ra) {
        try {
            service.save(obj);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @Override
    @GetMapping(URI + "/{id}/update")
    public String update(Article obj, @PathVariable(name = "id") Long id, RedirectAttributes ra) {
        try {
            service.update(obj);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @Override
    @GetMapping(URI + "/{id}/delete")
    public String delete(Article obj, @PathVariable(name = "id") Long id, RedirectAttributes ra) {
        try {
            service.delete(obj);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
