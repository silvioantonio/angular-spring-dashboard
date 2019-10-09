package com.silvio.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoLista {
	TRABALHO("Trabalho"), ESCOLA("Escola"), CASA("Casa");
	private final String nome;
	
	TipoLista(String nome) {
		this.nome = nome;
	}
	
	@JsonCreator
	public String getNome() {
		return this.nome;
	}
	
}
