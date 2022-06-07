package com.felixsilva.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felixsilva.cursomc.domain.model.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer clienteId;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message="O tamanho deve ser entre 5 e 250 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente obj) {
		super();
		this.clienteId = obj.getClienteId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}
	
	

}
