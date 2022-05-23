package com.felixsilva.cursomc.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pedidoId;
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntre;
	
	public Pedido() {
		super();
	}

	public Pedido(Integer pedidoId, LocalDateTime instante, Cliente cliente, Endereco enderecoDeEntre) {
		super();
		this.pedidoId = pedidoId;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntre = enderecoDeEntre;
	}
	
	

}
