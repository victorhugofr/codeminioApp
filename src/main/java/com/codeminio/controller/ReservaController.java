package com.codeminio.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import com.codeminio.dominio.Reserva;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping(value = "/create")
    public String create(Model model) {

        Reserva reserva = new Reserva();
        model.addAttribute("reserva", reserva);

        return "reserva/create";
    }

    @PostMapping
    public String store(Principal principal, Model model, Reserva reserva) {
        System.out.println(reserva.getData());
        try {

            String username = principal.getName();

            reservaService.store(username, reserva);

            return "redirect:reserva/create";

        } catch (RegraNegocioException e) {

            List<String> errors = e.getErrorList();
            model.addAttribute("errors", errors);

            return "reserva/create";
        }
    }
}
