package com.codeminio.extensions.controllers;

import java.security.Principal;
import java.util.List;

import com.codeminio.controller.ReservaController;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.extensions.dtos.ReservaCondominioDTO;
import com.codeminio.extensions.models.Area;
import com.codeminio.extensions.models.ReservaCondominio;
import com.codeminio.service.RecursoService;
import com.codeminio.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/reserva")
public class ReservaCondominioController extends ReservaController {

    @Autowired
    private ReservaService<ReservaCondominio, Area> reservaService;

    @Autowired
    private RecursoService<Area> recursoService;

    @GetMapping
    public String index(Model model) {
        List<ReservaCondominio> reservas = reservaService.listar();

        System.out.println("teste");

        for (ReservaCondominio reservaCondominio : reservas) {
            System.out.println(reservaCondominio.getData());
        }

        model.addAttribute("reservas", reservas);

        return "reserva/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        ReservaCondominioDTO reservaCondominioDTO = new ReservaCondominioDTO();
        List<Area> areas = recursoService.listar();

        model.addAttribute("reservaCondominioDTO", reservaCondominioDTO);
        model.addAttribute("areas", areas);

        return "reserva/create";
    }

    @PostMapping(value = "/create")
    public String store(Principal principal, Model model, ReservaCondominioDTO reservaCondominioDTO) {
        try {
            String username = principal.getName();

            // System.out.println(reservaCondominioDTO.getIdRecurso());
            // System.out.println(reservaCondominioDTO.getData());

            // for (VisitanteCondominioDTO visitante : reservaCondominioDTO.getVisitantes())
            // {
            // System.out.println(visitante.getCpf());
            // System.out.println(visitante.getNome());
            // }

            reservaService.cadastrar(username, reservaCondominioDTO);

            return "redirect:create";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();
            List<Area> areas = recursoService.listar();

            model.addAttribute("errors", errors);
            model.addAttribute("areas", areas);

            return "reserva/create";
        }
    }
}
