package com.codeminio.extensions.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codeminio.dominio.Usuario;
import com.codeminio.dominio.Visita;
import com.codeminio.dtos.ReservaDTO;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.extensions.dtos.ReservaCondominioDTO;
import com.codeminio.extensions.dtos.VisitanteCondominioDTO;
import com.codeminio.extensions.models.Area;
import com.codeminio.extensions.models.ReservaCondominio;
import com.codeminio.repository.RecursoRepository;
import com.codeminio.repository.ReservaRepository;
import com.codeminio.repository.UsuarioRepository;
import com.codeminio.repository.VisitaRepository;
import com.codeminio.service.impl.ReservaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaCondominioImpl extends ReservaServiceImpl<ReservaCondominio, Area> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository<ReservaCondominio, Area> reservaRepository;

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private RecursoRepository<Area> recursoRepository;

    @Override
    public void cadastrar(String username, ReservaDTO reservaDTO) {

        ReservaCondominioDTO reservaCondominioDTO = (ReservaCondominioDTO) reservaDTO;

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

        Optional<Area> area = recursoRepository.findById(reservaCondominioDTO.getIdRecurso());

        List<String> errors = new ArrayList<String>();

        LocalDate data;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.parse(reservaCondominioDTO.getData(), formatter);
        } catch (Exception e) {
            data = null;
        }

        if (!usuario.isPresent()) {
            errors.add("Usuário inexistente");
        }

        if (!area.isPresent()) {
            errors.add("Área inexistente");
        }

        if (reservaCondominioDTO.getData().isEmpty() || reservaCondominioDTO.getData().trim().isEmpty()) {
            errors.add("Data inválida");
        } else if (data.isBefore(LocalDate.now())) {
            errors.add("Você não pode reservar em uma data passada");
        }

        boolean flag = false;
        for (VisitanteCondominioDTO visita : reservaCondominioDTO.getVisitantes()) {
            if ((visita.getNome().isEmpty() || visita.getCpf().isEmpty()) && flag == false) {
                errors.add("Por favor preencha todos os campos dos visitantes");
                flag = true;
            }
        }

        boolean reservaExiste = reservaRepository.existsByDataAndRecurso(data, area.get());

        if (reservaExiste) {
            errors.add("Já existe uma reserva para o(a) " + area.get().getNomeDaArea() + " neste dia");
        }

        if (!errors.isEmpty()) {
            throw new RegraNegocioException(errors);
        }

        List<Visita> listaVisitantes = new ArrayList<Visita>();

        for (VisitanteCondominioDTO visita : reservaCondominioDTO.getVisitantes()) {
            Optional<Visita> visitanteAchado = visitaRepository.findByCpf(visita.getCpf());

            if (!visitanteAchado.isPresent()) {
                Visita novoVisitante = new Visita();

                novoVisitante.setCpf(visita.getCpf());
                novoVisitante.setNome(visita.getCpf());
                novoVisitante.setAtivo(true);

                visitaRepository.save(novoVisitante);

                listaVisitantes.add(novoVisitante);
            } else {
                listaVisitantes.add(visitanteAchado.get());
            }
        }

        ReservaCondominio reservaCondominio = new ReservaCondominio();

        reservaCondominio.setRecurso(area.get());
        reservaCondominio.setData(data);
        reservaCondominio.setAtivo(true);
        reservaCondominio.setUsuario(usuario.get());
        reservaCondominio.setVisitantes(listaVisitantes);

        reservaRepository.save(reservaCondominio);
    }

}
