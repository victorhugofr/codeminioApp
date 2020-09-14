package com.codeminio.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.codeminio.enums.CodigoMensagem;


@Component
public class RecuperaMensagem {

	@Autowired
	private MessageSource messageSource;
	
	public String getMensagem(CodigoMensagem codigo) {
		return messageSource.getMessage(codigo.getCodigo(), null, LocaleContextHolder.getLocale());
	}
}
