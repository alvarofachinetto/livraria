package com.livros.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.livros.model.enums.PermissaoUsuario;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	@Column(length = 70, nullable = false, unique = true)
	private String email;
	@Column(length = 15, nullable = false, unique = true)
	private String senha;
	private PermissaoUsuario permissaoUsuario;
	
	public Usuario() {}
	
	public Usuario(Long idUser, String email, String senha, PermissaoUsuario permissaoUsuario) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.senha = senha;
		this.permissaoUsuario = permissaoUsuario;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PermissaoUsuario getPermissaoUsuario() {
		return permissaoUsuario;
	}
	public void setPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
		this.permissaoUsuario = permissaoUsuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
	
	
	
}
