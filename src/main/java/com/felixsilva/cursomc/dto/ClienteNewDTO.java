package com.felixsilva.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felixsilva.cursomc.domain.service.validation.ClienteInsert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message="O tamanho deve ser entre 5 e 250 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	public ClienteNewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3,
			Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.cidadeId = cidadeId;
	}
	
	

}
