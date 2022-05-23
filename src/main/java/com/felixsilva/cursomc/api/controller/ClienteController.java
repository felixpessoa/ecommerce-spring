package com.felixsilva.cursomc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.service.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}
	
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> findByIdCliente(@PathVariable Integer clienteId){
		Cliente obj = clienteService.findById(clienteId);
		return ResponseEntity.status(HttpStatus.OK).body(obj); 
	}
	
	
	
	
}
