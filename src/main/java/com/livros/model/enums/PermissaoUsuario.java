package com.livros.model.enums;

public enum PermissaoUsuario {
	ADMIN(1, "ROLE_ADMIN"),
	USER(2, "ROLE_USER");
	
	private int cod;
	private String descricao;
	
	private PermissaoUsuario(int cod, String descricao) {
		this.setCod(cod);
		this.setDescricao(descricao);
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static PermissaoUsuario toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PermissaoUsuario p : PermissaoUsuario.values()) {
			if(cod.equals(p.cod)) {
				return p;
			}
		}
		throw new IllegalArgumentException();
	}
	
	
	
}
