package com.codeminio.controller;

import com.codeminio.dtos.EnqueteDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.EnqueteService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/enquete")
public class EnqueteController {

    @Autowired
    private EnqueteService enqueteService;

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
