package com.app.ecommerce.controllers;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface BaseController<T, S> {
    String getAll(Model model);
    String getById(S id,Model model);
    String add(Model model);
    String post(T obj, RedirectAttributes ra);
    String update(T obj,S id, RedirectAttributes ra);
    String delete(T obj,S id, RedirectAttributes ra);
}
