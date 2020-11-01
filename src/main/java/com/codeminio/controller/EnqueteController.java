package com.codeminio.controller;

import com.codeminio.dominio.Enquete;
import com.codeminio.dtos.EnqueteDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.EnqueteService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/enquete")
public class EnqueteController {

    @Autowired
    private EnqueteService enqueteService;

    @GetMapping
    public String index(Model model) {
        List<Enquete> enquetes = enqueteService.index();
        model.addAttribute("enquetes", enquetes);

        return "enquete/index";
    }

    // Validar parametros inválidos na url
    @GetMapping(value = "{id}")
    public String show(Principal principal, Model model, @PathVariable("id") int idEnquete) {
        String username = principal.getName();

        Enquete enquete = enqueteService.show(username, idEnquete);
        model.addAttribute("enquete", enquete);

        return "enquete/show";
    }

    @PostMapping(value = "{id}")
    public String update(Principal principal, Model model, @PathVariable("id") int idEnquete, int alternativa) {
        try {
            System.out.println(idEnquete);
            System.out.println(alternativa);
            return "redirect:";
        } catch (Exception e) {

            return "enquete/show";
        }
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        EnqueteDTO enqueteDTO = new EnqueteDTO();
        model.addAttribute("enqueteDTO", enqueteDTO);

        return "enquete/create";
    }

    @PostMapping(value = "/create")
    public String store(Principal principal, Model model, EnqueteDTO enqueteDTO) {
        try {
            String username = principal.getName();

            enqueteService.store(username, enqueteDTO);
            model.addAttribute("success", true);

            return "redirect:create";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();
            model.addAttribute("errors", errors);

            return "enquete/create";
        }
    }

    @PostMapping(value = "/create", params = { "addField" })
    public String addField(Model model, EnqueteDTO enqueteDTO) {
        enqueteDTO.setAlternativas("");
        model.addAttribute("enqueteDTO", enqueteDTO);

        return "enquete/create";
    }
}
