package com.felixsilva.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felixsilva.cursomc.domain.model.Categoria;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer categoriaId;
	
	@NotEmpty(message = "Preencimento obrigatorio")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") 
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
