package com.codeminio.controller;

import java.util.List;

import com.codeminio.dominio.Aviso;
import com.codeminio.service.AvisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sistema/aviso")
public class AvisoController {

  @Autowired
  private AvisoService avisoService;

  @GetMapping(value = "/create")
  public String create(Model model) {

    Aviso aviso = new Aviso();
    model.addAttribute("aviso", aviso);

    return "aviso/create";
  }

  // @GetMapping(value = "/aviso")
  // public ResponseEntity<List<Aviso>> index() {

  //   List<Aviso> avisos = service.index();

  //   return new ResponseEntity<List<Aviso>>(avisos, HttpStatus.OK);
  // }

  // @GetMapping(value = "/aviso/{idAviso}")
  // public ResponseEntity<Aviso> show(@PathVariable(value = "idAviso") Integer idAviso) {

  //   Aviso aviso = service.show(idAviso);

  //   return new ResponseEntity<Aviso>(aviso, HttpStatus.OK);
  // }

  // @GetMapping(value = "/funcionario/{idFuncionario}/aviso")
  // public ResponseEntity<List<Aviso>> index(@PathVariable(value = "idFuncionario") Integer idFuncionario) {

  //   List<Aviso> avisos = service.index(idFuncionario);

  //   return new ResponseEntity<List<Aviso>>(avisos, HttpStatus.OK);
  // }

  // @GetMapping(value = "/funcionario/{idFuncionario}/aviso/{idAviso}")
  // public ResponseEntity<Aviso> show(@PathVariable(value = "idFuncionario") Integer idFuncionario,
  //     @PathVariable(value = "idAviso") Integer idAviso) {

  //   Aviso aviso = service.show(idFuncionario, idAviso);

  //   return new ResponseEntity<Aviso>(aviso, HttpStatus.OK);
  // }

  // @PostMapping(value = "/funcionario/{idFuncionario}/aviso")
  // public ResponseEntity<Aviso> store(@PathVariable(value = "idFuncionario") Integer idFuncionario,
  //     @RequestBody Aviso aviso) {

  //   Aviso novoAviso = service.store(idFuncionario, aviso);

  //   return new ResponseEntity<Aviso>(novoAviso, HttpStatus.CREATED);
  // }

  // @PutMapping(value = "/funcionario/{idFuncionario}/aviso/{idAviso}")
  // public ResponseEntity<Aviso> update(@PathVariable(value = "idFuncionario") Integer idFuncionario,
  //     @PathVariable(value = "idAviso") Integer idAviso, @RequestBody Aviso aviso) {

  //   Aviso novoAviso = service.update(idFuncionario, idAviso, aviso);

  //   return new ResponseEntity<Aviso>(novoAviso, HttpStatus.OK);
  // }

  // @DeleteMapping(value = "/funcionario/{idFuncionario}/aviso/{idAviso}")
  // public ResponseEntity<String> delete(@PathVariable(value = "idFuncionario") Integer idFuncionario,
  //     @PathVariable(value = "idAviso") Integer idAviso) {

  //   service.delete(idFuncionario, idAviso);

  //   return new ResponseEntity<String>("Aviso excluído com sucesso", HttpStatus.OK);
  // }

}