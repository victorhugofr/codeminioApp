package com.codeminio.extensions.controllers;

import java.util.ArrayList;

import com.codeminio.dominio.ReservaCondominio;
import com.codeminio.extensions.dtos.ReservaCondominioDTO;
import com.codeminio.extensions.models.Area;
import com.codeminio.service.RecursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/reserva")
public class ReservaCondominioController {

    // @Autowired
    // private ReservaService reservaService;

    @Autowired
    private RecursoService<Area> recursoService;

    // @GetMapping
    // public String index(Model model) {
    // List<Reserva> reservas = reservaService.index();

    // model.addAttribute("reservas", reservas);

    // return "reserva/index";
    // }

    @GetMapping(value = "/create")
    public String create(Model model) {
        ReservaCondominioDTO reservaCondominioDTO = new ReservaCondominioDTO();
        ArrayList<Area> areas = new ArrayList<Area>(recursoService.listar());

        model.addAttribute("reservaCondominioDTO", reservaCondominioDTO);
        model.addAttribute("areas", areas);

        return "reserva/create";
    }

    // @PostMapping
    // public String store(Principal principal, Model model, ReservaCondominioDTO
    // reservaCondominioDTO) {
    // try {
    // String username = principal.getName();

    // // reservaService.store(username, reservaCondominioDTO);

    // return "redirect:reserva/create";
    // } catch (RegraNegocioException e) {
    // List<String> errors = e.getErrorList();
    // model.addAttribute("errors", errors);

    // return "reserva/create";
    // }
    // }
}
