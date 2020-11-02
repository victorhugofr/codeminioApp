package com.codeminio.dominio;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Documento extends AuditedEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DOCUMENTO")
	@SequenceGenerator(name="SEQ_DOCUMENTO", sequenceName="id_seq_documento", allocationSize=1)
	private Integer id;
	
	private String nome;

	@Column(columnDefinition="text")
	private String arquivoBase64;
	
	private String extensao; //pdf,docx
	
	@ManyToOne
	@JoinColumn(name="id_morador")
	private Morador morador;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] arquivoBase64Original;
	
	@Transient
	private MultipartFile documento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArquivoBase64() {
		return arquivoBase64;
	}

	public void setArquivoBase64(String arquivoBase64) {
		this.arquivoBase64 = arquivoBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getArquivoBase64Original() {
		return arquivoBase64Original;
	}

	public void setArquivoBase64Original(byte[] arquivoBase64Original) {
		this.arquivoBase64Original = arquivoBase64Original;
	}

	public MultipartFile getDocumento() {
		return documento;
	}

	public void setDocumento(MultipartFile documento) {
		this.documento = documento;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	
}
