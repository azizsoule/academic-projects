package com.app.ecommerce.controllers;

import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.services.UserGroupService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
public class GroupUserController {

    private static final String URI = "/groups";
    private static final String ADD_URI = "/add-group";
    private static final String LIST_VIEW = "user_group";
    private static final String VIEW = "add_user_group";
    private static final String EDIT_VIEW = "edit_user_group";

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping(URI)
    private String getUsersGroup(Model model) {
        model.addAttribute("userGroups", userGroupService.findAll().stream().filter(userGroup -> !userGroup.getLabel().equals("SUPER_ADMIN")).collect(Collectors.toList()));
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getUserGroup(Model model) {
        model.addAttribute("userGroup", new UserGroup());
        return VIEW;
    }

    @GetMapping(URI + "/{id}")
    private String getUserId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userGroup", userGroupService.findById(id));
        return EDIT_VIEW;
    }

    @PostMapping(ADD_URI + "/save")
    private String postUserGroup(UserGroup userGroup, RedirectAttributes ra) {
        try {
            userGroupService.save(userGroup);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    @PostMapping(URI + "/{id}/update")
    private String updateUserGroup(@PathVariable("id") Long id, UserGroup userGroup,
                              RedirectAttributes ra) {
        try {
            userGroupService.update(userGroup);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }

    @PostMapping(URI + "/{id}/delete")
    private String deleteUserGroup(@PathVariable("id") Long id, UserGroup userGroup, RedirectAttributes ra) {
        try {
            userGroupService.delete(userGroup);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }

}
