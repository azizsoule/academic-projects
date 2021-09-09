package com.app.ecommerce.controllers;


import com.app.ecommerce.dtos.UserDTO;
import com.app.ecommerce.models.User;
import com.app.ecommerce.services.UserService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private static final String URI = "/users";
    private static final String ADD_URI = "/add-user";
    private static final String LIST_VIEW = "user";
    private static final String VIEW = "add_user";
    /*private static final String EDIT_VIEW = "edit_catalog";*/

    @Autowired
    private UserService userService;

    @GetMapping(URI)
    private String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getUser(Model model) {
        model.addAttribute("user", new User());
        return VIEW;
    }

  /*  @GetMapping(URI + "/{id}")
    private String getUserId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return EDIT_VIEW;
    }*/

    @PostMapping(ADD_URI + "/save")
    private String postUser(UserDTO userDTO, RedirectAttributes ra) {
        try {
            userService.save(userDTO);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @PostMapping(URI + "/{id}/update")
    private String updateUser(@PathVariable("id") Long id, UserDTO userDTO,
                                 RedirectAttributes ra) {
        try {
            userService.update(userDTO);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, UserDTO userDTO, RedirectAttributes ra) {
        try {
            userService.delete(userDTO);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }

}
