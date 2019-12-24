package com.livros.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.model.Livro;
import com.livros.repository.LivroRepository;

@Service
public class H2Service {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void loadH2() throws Exception{
		Livro [] livros = {
			new Livro("50 Tons de Cinza", "E. L. James", "Vitage Books", 110, 19.90),
			new Livro("Pedro Nava - Leitor de Drummond", "Pedro Lenza", "Saraiva", 95, 25.90),
			new Livro("Do Luto ao Nascimento", "Camila Cabral", "Saraiva", 160, 30.00),
			new Livro("Um Caminho Para A Liberdade", "Jojo Moyes", "Saraiva", 368, 39.90)
		};
		
		Iterable<Livro> livrosIt = Arrays.asList(livros);
		livroRepository.saveAll(livrosIt);
	}
}
