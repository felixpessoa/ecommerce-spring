package com.felixsilva.cursomc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
	
}
