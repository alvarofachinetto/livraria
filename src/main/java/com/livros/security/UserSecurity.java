package com.livros.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.livros.model.enums.PermissaoUsuario;

public class UserSecurity implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String password;
	private String username;
	private SimpleGrantedAuthority authoritie;
	
	public UserSecurity() {}
	
	public UserSecurity(Long id, String password, String username, PermissaoUsuario permissoesUser) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.authoritie = new SimpleGrantedAuthority(permissoesUser.getDescricao());
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	public boolean hasRole(PermissaoUsuario permissao) {
		return getAuthorities().contains(new SimpleGrantedAuthority(permissao.getDescricao()));
	}
}
