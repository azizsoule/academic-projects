package com.app.ecommerce.controllers;

import com.app.ecommerce.models.User;
import com.app.ecommerce.utils.Router;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final String URI="/login";
    private static final String VIEW="login";

    @GetMapping(URI)
    private String getLogin(@AuthenticationPrincipal User user){
        if(user!=null){
            return Router.redirectTo("/");
        }
        return VIEW;
    }

}
