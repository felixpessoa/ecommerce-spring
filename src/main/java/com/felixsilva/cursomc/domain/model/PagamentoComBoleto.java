package com.felixsilva.cursomc.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataVencimento;
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
		super();
	}
	public PagamentoComBoleto(Integer pagamentoId, EstadoPagamento estador, Pedido pedido,LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
		super(pagamentoId, estador, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	
}
