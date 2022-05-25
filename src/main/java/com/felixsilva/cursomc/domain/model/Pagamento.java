package com.felixsilva.cursomc.domain.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;


@Entity
@Inheritance(strategy =InheritanceType.JOINED )
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer pagamentoId;
	private Integer estador;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento() {
		super();
	}

	public Pagamento(Integer pagamentoId, EstadoPagamento estador, Pedido pedido) {
		super();
		this.pagamentoId = pagamentoId;
		this.estador = estador.getCod();
		this.pedido = pedido;
	}

	public Integer getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(Integer pagamentoId) {
		this.pagamentoId = pagamentoId;
	}

	public EstadoPagamento getEstador() {
		return EstadoPagamento.toEnum(estador);
	}

	public void setEstador(EstadoPagamento estador) {
		this.estador = estador.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pagamentoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(pagamentoId, other.pagamentoId);
	}

	@Override
	public String toString() {
		return "Pagamento [pagamentoId=" + pagamentoId + ", estador=" + estador + ", pedido=" + pedido + "]";
	}
	
	
	
	
	
	
}
