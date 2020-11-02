package com.codeminio.controller;

import java.security.Principal;
import java.util.List;

import com.codeminio.dominio.Aviso;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/aviso")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @GetMapping(value = "/create")
    public String create(Model model) {
        Aviso aviso = new Aviso();
        model.addAttribute("aviso", aviso);

        return "aviso/create";
    }

    @PostMapping
    public String store(Principal principal, Model model, Aviso aviso) {
        try {
            String username = principal.getName();
            avisoService.store(username, aviso);

            return "redirect:index";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();
            model.addAttribute("errors", errors);

            return "aviso/create";
        }
    }
}