package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.CatalogDTO;
import com.app.ecommerce.dtos.DictionaryDTO;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.services.DictionaryService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentFilterController {
    private static final String URI="/comment_filter";
    private static final String ADD_URI="/add-dictionary";
    private static final String LIST_VIEW="dictionnary";
    private static final String VIEW="add_dictionnary";
    private static final String EDIT_VIEW="edit_dictionnary";

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping(URI)
    private String getWords(Model model){
        model.addAttribute("comment_filter",dictionaryService.findAll());
            return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String Word(Model model){
        model.addAttribute("dictionaryDTO",new DictionaryDTO());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getWordById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dictionaryDTO", dictionaryService.findById(id));
        return EDIT_VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postWord(DictionaryDTO dictionaryDTO, RedirectAttributes ra) {
        try {
            dictionaryService.save(dictionaryDTO);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

   @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, DictionaryDTO dictionaryDTO, RedirectAttributes ra) {
        try {
            dictionaryService.delete(dictionaryDTO);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }

}
