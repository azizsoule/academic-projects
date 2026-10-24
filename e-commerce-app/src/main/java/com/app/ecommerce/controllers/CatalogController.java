package com.app.ecommerce.controllers;

import com.app.ecommerce.models.Catalog;
import com.app.ecommerce.services.CatalogService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CatalogController {
    private static final String URI = "/catalogs";

    @Autowired
    private CatalogService catalogService;

    //READ
    @GetMapping(URI)
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(catalogService.findAll(), HttpStatus.OK);
    }
}
