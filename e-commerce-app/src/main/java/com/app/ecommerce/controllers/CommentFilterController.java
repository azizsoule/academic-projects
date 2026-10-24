package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.DictionaryDTO;
import com.app.ecommerce.models.Dictionary;
import com.app.ecommerce.services.CommentService;
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
    private static final String URI="/dictionnary";
    private static final String C_URI="/comment-filter";
    private static final String ADD_URI="/add-dictionary";
    private static final String LIST_VIEW="dictionnary";
    private static final String COMMENT_LIST_VIEW="comments";
    private static final String VIEW="add_dictionnary";
    private static final String EDIT_VIEW="edit_dictionnary";

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CommentService commentService;

    @GetMapping(URI)
    private String getWords(Model model){
        model.addAttribute("words",dictionaryService.findAll());
        model.addAttribute("blockedCommentCount",commentService.blockedComments().size());
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
    private String postWord(Dictionary dictionaryDTO, RedirectAttributes ra) {
        try {
            dictionaryService.save(dictionaryDTO);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }
    @PostMapping(URI + "/{id}/update")
    private String updateWord(@PathVariable("id") Long id, Dictionary dictionaryDTO, RedirectAttributes ra) {
        try {
            dictionaryService.update(dictionaryDTO);
            ra.addAttribute("id",id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id",id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

   @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, Dictionary dictionaryDTO, RedirectAttributes ra) {
        try {
            dictionaryService.delete(dictionaryDTO);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }

    @GetMapping(C_URI)
    private String getBlockedComment(Model model){
        model.addAttribute("blockedComments",commentService.blockedComments());
        model.addAttribute("comments",commentService.unblockedComments());
        return COMMENT_LIST_VIEW;
    }
    @GetMapping(C_URI+ "/{id}/block")
    private String blockComment(@PathVariable("id") Long id, RedirectAttributes ra){
        try {
            commentService.block(id);
            ra.addFlashAttribute("success", "Successfully blocked !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Block failure !");
        }
        return Router.redirectTo(C_URI);
    }
    @GetMapping(C_URI+ "/{id}/unblock")
    private String unBlockComment(@PathVariable("id") Long id, RedirectAttributes ra){
        try {
            commentService.unblock(id);
            ra.addFlashAttribute("success", "Successfully unblock comment !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Unblock failure !");
        }
        return Router.redirectTo(C_URI);
    }

}
