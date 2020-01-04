package com.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.livros.model.Usuario;
import com.livros.repository.UsuarioRepository;
import com.livros.security.UserSecurity;

@Component
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	public UsuarioRepository userRep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = userRep.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado "+ Usuario.class+ " email "+ username);
		}
		return new UserSecurity(usuario.getIdUser(), 
				usuario.getSenha(), 
				usuario.getEmail(), usuario.getPermissaoUsuario());
	}

}
