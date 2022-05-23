package com.felixsilva.cursomc.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.repository.ClienteRepository;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
