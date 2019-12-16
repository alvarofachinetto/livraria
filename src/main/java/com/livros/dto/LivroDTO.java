package com.livros.dto;

import java.io.Serializable;

import com.livros.model.Livro;

public class LivroDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idLivro;
	private String titulo;
	private String autor;
	private String editora;
	private Integer paginas;
	private Double preco;
	
	public LivroDTO() {}
	
	public LivroDTO(Livro livro) {
		this.idLivro = livro.getIdLivro();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.editora = livro.getEditora();
		this.paginas = livro.getPaginas();
		this.preco = livro.getPreco();
	}
	
	public Long getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
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
