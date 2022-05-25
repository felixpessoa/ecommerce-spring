package com.felixsilva.cursomc.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cidadeId;
	private String nomeCidade;
	
	
	@ManyToOne
	@JoinColumn(name="estador_id")
	private Estado estado;

	public Cidade() {
		super();
	}

	public Cidade(Integer cidadeId, String nomeCidade, Estado estado) {
		super();
		this.cidadeId = cidadeId;
		this.nomeCidade = nomeCidade;
		this.estado = estado;
	}

}
