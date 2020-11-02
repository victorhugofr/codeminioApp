package com.codeminio.dominio;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario extends AuditedEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "id_seq_usuario", allocationSize = 1)
	private Integer id;

	private String email;

	private String nome;

	private String senha;

	private String login;

	private String cpf;

	private String telefone;

	private String codigoRecuperarSenha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_role",
			uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id", "role_id" }, name = "unique_role_user"),
			joinColumns = @JoinColumn(name = "usuario_id",
							referencedColumnName="id", 
							table="usuario"),
			
			inverseJoinColumns = @JoinColumn(name = "role_id",
									referencedColumnName = "id",
									table="role")
	) //cria tabela de acesso do usuario
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		this.cpf = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodigoRecuperarSenha() {
		return codigoRecuperarSenha;
	}

	public void setCodigoRecuperarSenha(String codigoRecuperarSenha) {
		this.codigoRecuperarSenha = codigoRecuperarSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", login=" + login
				+ ", cpf=" + cpf + ", telefone=" + telefone + ", codigoRecuperarSenha=" + codigoRecuperarSenha + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
