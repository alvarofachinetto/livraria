package com.livros.response;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livros.model.Livro;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroResponse extends RepresentationModel<LivroResponse>{

	private Long livroId;
	private String titulo;
	private String autor;
	private String editora;
	private Integer paginas;
	private Double preco;
	
	
	public Long getLivroId() {
		return livroId;
	}
	public void setLivroId(Livro livro) {
		this.livroId = livro.getIdLivro();
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public Integer getPaginas() {
		return paginas;
	}
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
