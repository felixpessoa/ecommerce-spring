package com.felixsilva.cursomc.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.PagamentoComBoleto;
import com.felixsilva.cursomc.domain.model.Pedido;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;
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
	
	public Pedido insert(Pedido obj) {
		obj.setPedidoId(null);
		obj.setInstante(LocalDateTime.now());
		obj.getPagamento().setEstador(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		if ( obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
	}

}
