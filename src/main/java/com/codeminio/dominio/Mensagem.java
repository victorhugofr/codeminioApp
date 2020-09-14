package com.codeminio.dominio;

import java.io.Serializable;


public class Mensagem implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	public static enum Tipo {
		DANGER {
			@Override
			public String css() {
				return "msg-error";
			}
		}, WARNING {
			@Override
			public String css() {
				return "msg-warnings";
			}
		}, INFO {
			@Override
			public String css() {
				return "msg-infos";
			}
		}, SUCCESS {
			@Override
			public String css() {
				return "msg-success";
			}
		};
		
		public abstract String css();
	}
	
	public static enum Layout {
		TOAST("mensagens"),
		ALERT("layoutMensagem");
		
		private String layout;
		
		Layout(String layout) {
			this.layout = layout;
		}
		
		public String get() {
			return this.layout;
		}
		
	}
	/**
	 * Atributo que representa o conteudo da mensagem
	 */
	private final String conteudo;
	/**
	 * Atributo que representa o tipo da mensagem
	 */
	private final Tipo tipo;
	private final Object[] args;

	public Mensagem(String conteudo, Tipo tipo) {
		this.conteudo = conteudo;
		this.tipo = tipo;
		this.args = null;
	}

	public Mensagem(String conteudo, Tipo tipo, Object... args) {
		this.conteudo = conteudo;
		this.tipo = tipo;
		this.args = args;
	}

	public String getConteudo() {
		return conteudo;
	}

	public String getTipo() {
		return tipo.toString().toLowerCase();
	}
	
	public String getCss() {
		return tipo.css();
	}

	public Object[] getArgs() {
		return args;
	}

}
