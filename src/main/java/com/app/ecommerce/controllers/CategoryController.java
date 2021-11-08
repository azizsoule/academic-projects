package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Image;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.services.CategoryService;
import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.MediaSaver;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/api")
public class CategoryController {
    private static final String URI = "/categories";

    @Autowired
    private CategoryService service;

    //READ
    @GetMapping(URI)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


}
