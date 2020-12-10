package com.codeminio.controller;

import java.security.Principal;
import java.util.List;

import com.codeminio.dominio.Area;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.RecursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/area")
public class RecursoCondominioController {

    @Autowired
    private RecursoService<Area> recursoService;

    @GetMapping(value = "/create")
    public String create(Model model) {

        List<Area> area = recursoService.listar();

        System.out.println("teste");

        for (Area area2 : area) {
            System.out.println(area2.getNomeDaArea());
        }
        return "area/create";
    }

    @PostMapping
    public String store(Principal principal, Model model, String nomeDaArea) {
        try {
            String username = principal.getName();

            recursoService.cadastrar(username, nomeDaArea);

            return "redirect:area/create";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();

            model.addAttribute("nomeDaArea", nomeDaArea);
            model.addAttribute("errors", errors);

            return "area/create";
        }
    }
}
