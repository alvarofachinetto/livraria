package com.livros.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.livros.model.Livro;
import com.livros.model.Usuario;
import com.livros.model.enums.PermissaoUsuario;
import com.livros.repository.LivroRepository;
import com.livros.repository.UsuarioRepository;

@Service
public class H2Service {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void loadH2() throws Exception{
		Usuario [] usuarios = {
			new Usuario(null, "alvaro.silva@gmail.com", encoder.encode("545445"), PermissaoUsuario.ADMIN),
			new Usuario(null, "sakura.hakashi@gmail.com", encoder.encode("225646"), PermissaoUsuario.USER)
		};
		
		Livro [] livros = {
			new Livro(null,"50 Tons de Cinza", "E. L. James", "Vitage Books", 110, 19.90),
			new Livro(null,"Pedro Nava - Leitor de Drummond", "Pedro Lenza", "Saraiva", 95, 25.90),
			new Livro(null,"Do Luto ao Nascimento", "Camila Cabral", "Saraiva", 160, 30.00),
			new Livro(null,"Um Caminho Para A Liberdade", "Jojo Moyes", "Saraiva", 368, 39.90)
		};
		
		Iterable<Livro> livrosIt = Arrays.asList(livros);
		livroRepository.saveAll(livrosIt);
		
		Iterable<Usuario> usuarioIt = Arrays.asList(usuarios);
		usuarioRepository.saveAll(usuarioIt);
		
	}
}
