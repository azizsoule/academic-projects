package com.app.ecommerce.controllers;

import com.app.ecommerce.models.*;
import com.app.ecommerce.services.ArticleService;
import com.app.ecommerce.services.CommentService;
import com.app.ecommerce.services.ImageService;
import com.app.ecommerce.services.SubCategoryService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @Override
    @GetMapping(URI)
    public String getAll(Model model) {
       List<Article> recommendedList = new ArrayList<>();
        List<Article> articles = service.findAll();
        articles.forEach(article -> {
             int i =1;
             if (article.getComments().size()!=0 && article.getRatingSum()!=0) {
                 if (article.getRatingSum() / article.getComments().size() >= 3 && i <= 5) {
                     recommendedList.add(article);
                     i +=1;
                 }
             }
        });
        System.out.println("***********************"+recommendedList.size());
        model.addAttribute("articles", articles);
        model.addAttribute("recommend",recommendedList);
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
        model.addAttribute("article", new Article());
        model.addAttribute("subCategories", subCategoryService.findAll());
        return ADD_VIEW;
    }

    @Override
    public String post(Article obj, RedirectAttributes ra) {
        return null;
    }

    @PostMapping(URI + "/save")
    public String post(Article obj, @RequestParam("articleImage") MultipartFile multipartFile, RedirectAttributes ra, @RequestParam("files") MultipartFile[] multipartFiles) {
        try {
//            ArrayList<MultipartFile> liste = new ArrayList<MultipartFile>(Arrays.asList(multipartFiles));
            Article article;
            if(!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                obj.setImage(fileName);
                article = service.save(obj);
                String uploadDir = Constants.ARTICLES_MEDIA_DIR+'/'+ article.getSku();
                MediaSaver.saveFile(uploadDir, fileName, multipartFile);
                for (MultipartFile file : multipartFiles) {
                    if (!file.isEmpty()) {
                        String fname = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                        Image image = new Image(fname);
                        image.setArticle(article);
                        imageService.save(image);
                        String uploadDir1 = Constants.ARTICLES_MEDIA_DIR + '/' + article.getSku();
                        MediaSaver.saveFile(uploadDir1, fname, file);
                    }
                }
//                liste.forEach(multipartFile1 -> {
//                    article.getImages().add(new Image(StringUtils.cleanPath(Objects.requireNonNull(multipartFile1.getOriginalFilename()))));
//                    String uploadDir1 = Constants.ARTICLES_MEDIA_DIR+'/'+ article.getSku();
//                    try {
//                        MediaSaver.saveFile(uploadDir1, fileName, multipartFile1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
            }
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @Override
    @PostMapping(URI + "/{id}/update")
    public String update(Article obj, @PathVariable(name = "id") Long id, RedirectAttributes ra) {
        try {
            service.update(obj);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}/edit");
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
