package com.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.model.Usuario;
import com.livros.repository.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAllUser(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findUser(Long idUser) throws ObjectNotFoundException {
		Usuario usuario = usuarioRepository.findById(idUser)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Usuario.class + " id "+ idUser));
		return usuario;
	}
	
	public Usuario saveUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deleteUser(Long idUser) throws ObjectNotFoundException {
		if(findUser(idUser) != null) {
			usuarioRepository.deleteById(idUser);
		}		
	}
}
