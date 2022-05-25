package com.felixsilva.cursomc.dto;

import java.io.Serializable;

import com.felixsilva.cursomc.domain.model.Categoria;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer categoriaId;
	private String nome;
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(Categoria obj) {
		super();
		this.categoriaId = obj.getCategoriaId();
		this.nome = obj.getNome();
	}



	
	
	
}
