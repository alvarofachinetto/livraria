package com.livros.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livros.controller.LivroController;
import com.livros.dto.LivroDTO;
import com.livros.model.Livro;
import com.livros.response.LivroResponse;

import javassist.tools.rmi.ObjectNotFoundException;

@Component
public class ResourceLivro {

	private ObjectMapper objectMapper;
	
	public ResourceLivro(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
		
	public LivroResponse criarLink(Livro livro) throws ObjectNotFoundException {
		LivroResponse livroResponse = objectMapper.convertValue(livro, LivroResponse.class);
		Link links = linkTo(methodOn(LivroController.class).findLivro(livro.getIdLivro())).withSelfRel();
		livroResponse.add(links);
		return livroResponse;
	}
	
	public LivroResponse criarLinkDetalhes(Livro livro) throws ObjectNotFoundException {
		LivroResponse livroResponse = objectMapper.convertValue(livro, LivroResponse.class);
		
		Link linkDelete = linkTo(methodOn(LivroController.class)
				.deleteLivro(livro.getIdLivro()))
				.withRel("remover").withTitle("delete book")
				.withType("delete");
		
		Link linkUpdate = linkTo(methodOn(LivroController.class)
				.updateLivro(new LivroDTO()))
				.withSelfRel()
				.withTitle("update book").withType("update");
		
		livroResponse.add(linkDelete, linkUpdate);
		return livroResponse;
	}

	
}
