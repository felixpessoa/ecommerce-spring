package com.felixsilva.cursomc.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felixsilva.cursomc.domain.model.Pedido;
import com.felixsilva.cursomc.domain.service.PedidoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;


	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}
	
	
	@GetMapping("/{pedidoId}")
	public ResponseEntity<Pedido> findByIdPedido(@PathVariable Integer pedidoId){
		Pedido obj = pedidoService.findById(pedidoId);
		return ResponseEntity.status(HttpStatus.OK).body(obj); 
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getPedidoId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	
}
