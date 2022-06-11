package com.felixsilva.cursomc.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.ItemPedido;
import com.felixsilva.cursomc.domain.model.PagamentoComBoleto;
import com.felixsilva.cursomc.domain.model.Pedido;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;
import com.felixsilva.cursomc.domain.repository.ItemPedidoRepository;
import com.felixsilva.cursomc.domain.repository.PagamentoRepository;
import com.felixsilva.cursomc.domain.repository.PedidoRepository;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
	private BoletoService boletoService;
	private PagamentoRepository pagamentoRepository;
	private ProdutoService produtoService;
	private ItemPedidoRepository itemPedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository, BoletoService boletoService, ProdutoService produtoService, 
			ItemPedidoRepository itemPedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
		this.boletoService = boletoService;
		this.produtoService = produtoService;
		this.itemPedidoRepository = itemPedidoRepository;
	}
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setPedidoId(null);
		obj.setInstante(LocalDateTime.now());
		obj.getPagamento().setEstador(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		if ( obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj  = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.findById(ip.getProduto().getProdutoId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
