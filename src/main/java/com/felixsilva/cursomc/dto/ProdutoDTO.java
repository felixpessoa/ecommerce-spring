package com.felixsilva.cursomc.dto;

import java.io.Serializable;

import com.felixsilva.cursomc.domain.model.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer produtoId;
	private String nomeProduto;
	private Double preco;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.produtoId = obj.getProdutoId();
		this.nomeProduto = obj.getNomeProduto();
		this.preco = obj.getPreco();
	}
	
}
