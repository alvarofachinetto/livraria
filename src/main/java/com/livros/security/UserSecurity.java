package com.livros.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.livros.model.enums.PermissaoUsuario;

public class UserSecurity implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String password;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSecurity() {}
	
	public UserSecurity(Long id, String password, String username, Set<PermissaoUsuario>permissoesUser) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.authorities = permissoesUser
								.stream()
								.map(permissao -> new SimpleGrantedAuthority(permissao.getDescricao()))
								.collect(Collectors.toList());
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

	public boolean hasRole(PermissaoUsuario roleUsuario) {
		return authorities
				.contains(
						new SimpleGrantedAuthority(roleUsuario.getDescricao()));
	}
	
}
