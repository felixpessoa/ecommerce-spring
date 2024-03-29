package com.felixsilva.cursomc.domain.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;


@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Integer pagamentoId, EstadoPagamento estador, Pedido pedido, Integer numeroDeParcelas) {
		super(pagamentoId, estador, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

	
}
