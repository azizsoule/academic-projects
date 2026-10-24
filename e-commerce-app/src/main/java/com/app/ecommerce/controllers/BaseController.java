package com.app.ecommerce.controllers;

import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Customer;
import com.app.ecommerce.services.CartItemService;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class BaseController {

    @Autowired
    CatalogService catalogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartItemService cartItemService;

    @ModelAttribute("catalogs")
    public List<Catalog> catalogs() {
        return catalogService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("userCartItems")
    public List<CartItem> userCartItems(@AuthenticationPrincipal Customer customer) {
        return cartItemService.findAllByCustomer(customer);
    }

}
