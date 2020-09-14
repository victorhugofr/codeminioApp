package com.codeminio.helper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeminio.enums.CodigoMensagem;
import com.codeminio.dominio.ListaMensagens;
import com.codeminio.dominio.Mensagem;

/**
 * Helper para envio de mensagens para usuarios do sistema.
 * @author luciooliveira
 *
 */
@Component
public final class MensagemHelper {
	
	@Autowired
	private RecuperaMensagem recuperaMensagem;
	
	/**
	 * Mensagens de {@link RedirectAttributes}
	 */
	public void sucesso(RedirectAttributes ra, CodigoMensagem codigo, Object... args) {
		addAtributo(ra, codigo, Mensagem.Tipo.SUCCESS, args);
	}

	public void erro(RedirectAttributes ra, CodigoMensagem codigo, Object... args) {
		addAtributo(ra, codigo, Mensagem.Tipo.DANGER, args);
	}
	
	public void informacao(RedirectAttributes ra, CodigoMensagem codigo, Object... args) {
		addAtributo(ra, codigo, Mensagem.Tipo.INFO, args);
	}

	public void cuidado(RedirectAttributes ra, CodigoMensagem codigo, Object... args) {
		addAtributo(ra, codigo, Mensagem.Tipo.WARNING, args);
	}

	private void addAtributo(RedirectAttributes ra, CodigoMensagem codigo, Mensagem.Tipo type, Object... args) {
		String i18nMensagem = recuperaMensagem.getMensagem(codigo);
		ra.addFlashAttribute(Mensagem.Layout.ALERT.get(), new Mensagem(i18nMensagem, type, args));
	}

	public void mensagens(RedirectAttributes ra, ListaMensagens mensagens) {
		ra.addFlashAttribute("erros", mensagens.getErros().stream().map((c) -> {return new Mensagem(recuperaMensagem.getMensagem(c), Mensagem.Tipo.DANGER);}).collect(Collectors.toList()));
		ra.addFlashAttribute("warnings", mensagens.getWarnings().stream().map((c) -> {return new Mensagem(recuperaMensagem.getMensagem(c), Mensagem.Tipo.WARNING);}).collect(Collectors.toList()));
		ra.addFlashAttribute("infos", mensagens.getInfos().stream().map((c) -> {return new Mensagem(recuperaMensagem.getMensagem(c), Mensagem.Tipo.INFO);}).collect(Collectors.toList()));
		ra.addFlashAttribute("success", mensagens.getSuccess().stream().map((c) -> {return new Mensagem(recuperaMensagem.getMensagem(c), Mensagem.Tipo.SUCCESS);}).collect(Collectors.toList()));
	}
	
	/**
	 * Mensagens de {@link Model}
	 */
	
	public void sucesso(Model model, CodigoMensagem codigo, Object... args) {
		addMensagem(model, codigo, Mensagem.Tipo.SUCCESS, args);
	}

	public void erro(Model model, CodigoMensagem codigo, Object... args) {
		addMensagem(model, codigo, Mensagem.Tipo.DANGER, args);
	}

	public void informacao(Model model, CodigoMensagem codigo, Object... args) {
		addMensagem(model, codigo, Mensagem.Tipo.INFO, args);
	}

	public void cuidado(Model model, CodigoMensagem codigo, Object... args) {
		addMensagem(model, codigo, Mensagem.Tipo.WARNING, args);
	}
	
	private void addMensagem(Model model, CodigoMensagem codigo, Mensagem.Tipo type, Object... args) {
		addMensagem(model, codigo, type, Mensagem.Layout.ALERT, args);
	}

	public void addMensagem(Model model, CodigoMensagem codigo, Mensagem.Tipo type, Mensagem.Layout layout, Object... args) {
		String i18nMensagem = recuperaMensagem.getMensagem(codigo);
		model.addAttribute(layout.get(), new Mensagem(i18nMensagem, type, args));
	}

}