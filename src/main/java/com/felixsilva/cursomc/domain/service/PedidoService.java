package com.felixsilva.cursomc.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Pedido;
import com.felixsilva.cursomc.domain.repository.PedidoRepository;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
