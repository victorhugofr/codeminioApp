package com.codeminio.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.codeminio.dominio.Reserva;
import com.codeminio.dominio.Visita;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sistema/reserva")
public class ReservaController {

    @GetMapping(value = "/create")
    public String create(Model model) {

        Reserva reserva = new Reserva();
        model.addAttribute("reserva", reserva);

        return "reserva/create";
    }

    @PostMapping
    public String store(Reserva reserva) {
        System.out.println(reserva.getNomeDaArea());
        System.out.println(reserva.getData());

        for (Visita visita : reserva.getVisitantes()) {
            System.out.println(visita.getNome());
            System.out.println(visita.getCpf());
        }

        return "redirect:reserva/create";
    }
}
