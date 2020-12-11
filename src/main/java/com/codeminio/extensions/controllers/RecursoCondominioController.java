package com.codeminio.extensions.controllers;

import java.security.Principal;
import java.util.List;

import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.extensions.dtos.RecursoCondominioDTO;
import com.codeminio.extensions.models.Area;
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

        RecursoCondominioDTO recursoCondominioDTO = new RecursoCondominioDTO();
        model.addAttribute("recursoCondominioDTO", recursoCondominioDTO);

        return "area/create";
    }

    @PostMapping
    public String store(Principal principal, Model model, RecursoCondominioDTO recursoCondominioDTO) {
        try {
            String username = principal.getName();

            recursoService.cadastrar(username, recursoCondominioDTO);

            return "redirect:area/create";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();

            // model.addAttribute("nomeDaArea", nomeDaArea);
            model.addAttribute("errors", errors);

            return "area/create";
        }
    }
}
