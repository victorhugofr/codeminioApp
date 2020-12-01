package com.codeminio.controller;

import com.codeminio.dominio.Area;
import com.codeminio.dominio.Recurso;
import com.codeminio.dominio.Reserva;
import com.codeminio.dtos.ReservaCondominioDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.RecursoService;
import com.codeminio.service.ReservaService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/reserva")
public class ReservaCondominioController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public String index(Model model) {
        List<Reserva> reservas = reservaService.index();

        model.addAttribute("reservas", reservas);

        return "reserva/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        ReservaCondominioDTO reserva = new ReservaCondominioDTO();

        List<Area> areas = (List<Area>) recursoService.listar();

        model.addAttribute("reserva", reserva);
        model.addAttribute("areas", areas);

        return "reserva/create";
    }

    @PostMapping
    public String store(Principal principal, Model model, ReservaCondominioDTO reservaCondominioDTO) {
        try {
            String username = principal.getName();

            // reservaService.store(username, reservaCondominioDTO);

            return "redirect:reserva/create";
        } catch (RegraNegocioException e) {
            List<String> errors = e.getErrorList();
            model.addAttribute("errors", errors);

            return "reserva/create";
        }
    }
}
