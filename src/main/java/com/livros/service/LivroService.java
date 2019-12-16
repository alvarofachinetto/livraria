package com.livros.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.dto.LivroDTO;
import com.livros.model.Livro;
import com.livros.repository.LivroRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	
	public List<Livro> findAll(){
		return livroRepository.findAll();
	}
	
	public Optional<Livro> findId(Long idLivro) throws ObjectNotFoundException{
		Optional<Livro> livroOp = livroRepository.findById(idLivro);
		livroOp
			.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + Livro.class + " /nvalor "+ idLivro));
		return livroOp;
	}
	
	@Transactional
	public Livro saveLivro(Livro livro) {
		livroRepository.save(livro);
		return livro;
	}
	
	public Livro updateLivro(Livro livro) {
		Livro newLivro = livroRepository.findById(livro.getIdLivro()).get();
		livroRepository.save(newLivro);
		
		return livro;
	}
	
	public Livro newLivroDto(LivroDTO livroDto) {
		return new Livro(livroDto.getIdLivro(), livroDto.getTitulo(), livroDto.getAutor(), 
				livroDto.getEditora(), livroDto.getPaginas(), livroDto.getPreco());
	}
	
	
	public void deleteLivro(Long idLivro) throws ObjectNotFoundException{
		Optional<Livro> livroOP = livroRepository.findById(idLivro);
		if(livroOP.isEmpty()) {
			livroOP
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + Livro.class + 
						" /nvalor "+ idLivro));
		}else {
			livroRepository.deleteById(idLivro);
		}
		
	}
}
