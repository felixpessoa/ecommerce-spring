package com.felixsilva.cursomc.domain.model.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {
	
	PEDENTE(1, "Pendete"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) { 
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
		
	}

}
