package com.livros.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livros.dto.LivroDTO;
import com.livros.model.Livro;
import com.livros.service.LivroService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/api/livros")
public class LivroResource {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/")
	public ResponseEntity<List<Livro>> listLivros(){
		List<Livro> livros = livroService.findAll();
		return ResponseEntity.ok().body(livros);
	}
	
	@GetMapping("/{idLivro}")
	public ResponseEntity<Optional<Livro>> findLivro(@PathVariable Long idLivro) throws ObjectNotFoundException{
		Optional<Livro> livro = livroService.findId(idLivro);
		return ResponseEntity.ok().body(livro);
	}
	
	@PostMapping("/livro")
	public ResponseEntity<Livro> saveLivro(@RequestBody Livro livro){
		livroService.saveLivro(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(livro);
	}
	
	@PutMapping("/{idLivro}")
	public ResponseEntity<Livro> updateLivro(@RequestBody LivroDTO livroDto){
		Livro livro = livroService.newLivroDto(livroDto);
		livroService.updateLivro(livro);
		return ResponseEntity.ok().body(livro);
	}
	
	@DeleteMapping("/{idlivro}")
	public ResponseEntity<Void> deleteLivro(@PathVariable Long idLivro) throws ObjectNotFoundException{
		livroService.deleteLivro(idLivro);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
