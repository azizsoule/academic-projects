package com.app.ecommerce.controllers;

import com.app.ecommerce.dtos.CatalogDTO;
import com.app.ecommerce.dtos.CategoryDTO;
import com.app.ecommerce.services.CatalogService;
import com.app.ecommerce.utils.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogController {
    private static final String URI = "/catalogs";
    private static final String ADD_URI = "/add-catalog";
    private static final String LIST_VIEW = "catalog";
    private static final String VIEW = "add_catalog";
    private static final String EDIT_VIEW = "edit_catalog";

    @Autowired
    private CatalogService catalogService;

    @GetMapping(URI)
    private String getCatalogs(Model model) {
        model.addAttribute("catalogs", catalogService.findAll());
        return LIST_VIEW;
    }

    @GetMapping(ADD_URI)
    private String getCatalog(Model model) {
        model.addAttribute("catalogDTO", new CatalogDTO());
        return VIEW;
    }
    /*Pour afficher un catalog particulier
    * on utilise une variable de chemin appelé pathVariable
    * C'est elle qui va nous permettre d'aller faire une recherceh en db
    * pour voir s'il existe un catalogue qui a pour id la valeur du pathvariable
    * Si oui on recupère les info relatives a ce catague et on envoie le tout a la vue*/
    @GetMapping(URI + "/{id}")
    private String getCatalogById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("catalogDTO", catalogService.findById(id));
        return EDIT_VIEW;
    }
    /*Pour l'insertion en bd on passe juste en paramètre de la methode l'objet qu'on souhaite
    * enregistrer en ensuite on utilise les services appropriés pour eefectuer la sauvegarde*/
    @PostMapping(ADD_URI + "/save")
    private String postCatalog(CatalogDTO catalogDTO, RedirectAttributes ra) {
        try {
            catalogService.save(catalogDTO);
            ra.addFlashAttribute("success", "Successfully saved !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Registration failure !");
        }
        return Router.redirectTo(ADD_URI);
    }

    /*Ici on réalise la mise à jour du catalogue avec l'id qui se trouve dans l'url(appellé pathvariable)
    * Si la mise a jour s'est bien passé on retourne sur la page de mise a jour du catalogue portant l'id dans l'url
    * avec un message de succès et un message d'échec sinon*/
    @PostMapping(URI + "/{id}/update")
    private String updateCatalog(@PathVariable("id") Long id, CatalogDTO catalogDTO,
                                 RedirectAttributes ra) {
        try {
            catalogService.update(catalogDTO);
            ra.addAttribute("id", id).addFlashAttribute("success", "Successfully updated !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addAttribute("id", id).addFlashAttribute("fail", "Update failure !");
        }
        return Router.redirectTo(URI + "/{id}");
    }
    /*Permet d'effacer le catalogue qui a pour id la valeur passée dans l'url(pathvariable) et on renvoi
    * un message de succes si la supression s'est bien effectué et fail sinon
    * NB: je n'ai pas encore terminé l'affichage des messages de succes ou d'echec en pratique
    * les methodes marchent correctements c'est juste que le message ne s'affiche pas car j'ai pas encore implémenté*/
    @PostMapping(URI + "/{id}/delete")
    private String deleteCatalog(@PathVariable("id") Long id, CatalogDTO catalogDTO, RedirectAttributes ra) {
        try {
            catalogService.delete(catalogDTO);
            ra.addFlashAttribute("success", "Successfully deleted !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ra.addFlashAttribute("fail", "Fail to delete !");
        }
        return Router.redirectTo(URI);
    }
}
