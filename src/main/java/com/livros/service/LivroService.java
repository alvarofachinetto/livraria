package com.livros.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livros.dto.LivroDTO;
import com.livros.model.Livro;
import com.livros.repository.LivroRepository;
import com.livros.resource.ResourceLivro;
import com.livros.response.LivroResponse;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ResourceLivro resourceLivro;
	
	public List<LivroResponse> findAll(){
		List<Livro> livros = livroRepository.findAll();
		List<LivroResponse> livrosResp = livros.stream()
												.map(it -> {
													try {
														return resourceLivro.criarLink(it);
													} catch (ObjectNotFoundException e) {
														new ObjectNotFoundException("Objeto não encontrado " + Livro.class + " /nvalor "+ it.getIdLivro());
													}
													return null;
												})
												.collect(Collectors.toList());
		return livrosResp;
	}
	
	public LivroResponse findId(Long idLivro) throws ObjectNotFoundException{
		Livro livroOp = livroRepository.findById(idLivro)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + Livro.class + " /nvalor "+ idLivro));;	
		LivroResponse livroResponse = resourceLivro.criarLinkDetalhes(livroOp);
		
		return livroResponse;
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
		return new Livro(livroDto.getTitulo(), livroDto.getAutor(), 
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
