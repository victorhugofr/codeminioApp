package com.codeminio.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.codeminio.dominio.Documento;
import com.codeminio.dominio.Morador;
import com.codeminio.exceptions.RegraNegocioException;
import com.codeminio.service.MoradorService;
import com.codeminio.service.impl.DocumentoServiceImpl;


@Controller /* Arquitetura REST */
@RequestMapping("/sistema/documento")
public class DocumentoController {

	@Autowired
	private DocumentoServiceImpl service;
	
	@Autowired
	private MoradorService moradorService;
	
	@PostMapping("/salvar")
	public ModelAndView cadastrar(Documento documento,  BindingResult br, RedirectAttributes ra) {
		ModelAndView modelAndView = null;
		try {
			service.salvarDocumento(documento);
			modelAndView = new ModelAndView(new RedirectView("index", true));
		} catch (RegraNegocioException e) {
			return modelAndView;
		}
		modelAndView = new ModelAndView(new RedirectView("/sistema/index", true));
		return modelAndView;
	}
	
	@GetMapping(value = "/form")
	public String cadastrar(Model modelo) {
		Documento novoDocumento = new Documento();
		List<Morador> moradores = moradorService.listarMoradores();
		modelo.addAttribute("documento",novoDocumento);
		modelo.addAttribute("moradores",moradores);
		return "documentos/cadastrar";
	}
	
	@GetMapping(value = "/listar")
	public String listar(Model model) {
		List<Documento> documentos = service.listarDocumentos();
		model.addAttribute("documentoLista", documentos);
		return "documentos/listar";
	}
	
	@GetMapping(value = "/baixar/{id}")
	public ResponseEntity<byte[]> baixarDocumento ( @PathVariable(value = "id") Integer id) throws IOException {
		Optional<Documento>documento = service.procurarPorId(id);
		
		return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + documento.get().getNome().replace(" ", "_"))
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_ENCODING,"identity")
                .body(documento.get().getArquivoBase64Original());
	}
	
}
