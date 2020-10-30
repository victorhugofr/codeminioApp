package com.codeminio.controller;

import com.codeminio.dtos.EnqueteDTO;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/enquete")
public class EnqueteController {

    @GetMapping(value = "/create")
    public String create(Model model) {
        EnqueteDTO enqueteDTO = new EnqueteDTO();
        model.addAttribute("enquete", enqueteDTO);

        return "enquete/create";
    }

    @PostMapping(value = "/create")
    public String store(Principal principal, Model model, EnqueteDTO enqueteDTO) {
        System.out.println(enqueteDTO.getTitulo());
        System.out.println(enqueteDTO.getDataLimite());

        for (String alternativa : enqueteDTO.getAlternativas()) {
            System.out.println(alternativa);
        }

        return "redirect:create";
    }

    @PostMapping(value = "/create", params = { "addField" })
    public String addField(Model model, EnqueteDTO enqueteDTO) {
        enqueteDTO.setAlternativas("");
        model.addAttribute("enquete", enqueteDTO);

        return "enquete/create";
    }
}
