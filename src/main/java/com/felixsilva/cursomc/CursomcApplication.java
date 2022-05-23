package com.felixsilva.cursomc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.model.Cidade;
import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.model.Endereco;
import com.felixsilva.cursomc.domain.model.Estado;
import com.felixsilva.cursomc.domain.model.ItemPedido;
import com.felixsilva.cursomc.domain.model.Pagamento;
import com.felixsilva.cursomc.domain.model.PagamentoComBoleto;
import com.felixsilva.cursomc.domain.model.PagamentoComCartao;
import com.felixsilva.cursomc.domain.model.Pedido;
import com.felixsilva.cursomc.domain.model.Produto;
import com.felixsilva.cursomc.domain.model.enums.EstadoPagamento;
import com.felixsilva.cursomc.domain.model.enums.TipoCliente;
import com.felixsilva.cursomc.domain.repository.CategoriaRepository;
import com.felixsilva.cursomc.domain.repository.CidadeRepository;
import com.felixsilva.cursomc.domain.repository.ClienteRepository;
import com.felixsilva.cursomc.domain.repository.EnderecoRepository;
import com.felixsilva.cursomc.domain.repository.EstadoRepository;
import com.felixsilva.cursomc.domain.repository.ItemPedidoRepository;
import com.felixsilva.cursomc.domain.repository.PagamentoRepository;
import com.felixsilva.cursomc.domain.repository.PedidoRepository;
import com.felixsilva.cursomc.domain.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	private CategoriaRepository categoriaRepository;
	private ProdutoRepository produtoRepository;
	private CidadeRepository cidadeRepository;
	private EstadoRepository estadoRepository;
	private ClienteRepository clieteClienteRepository;
	private EnderecoRepository enderecoRepository;
	private PedidoRepository pedidoRepository;
	private PagamentoRepository pagamentoRepository;
	private ItemPedidoRepository itemPedidoRepository;
	
	public CursomcApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CidadeRepository cidadeRepository, EstadoRepository estadoRepository, ClienteRepository clieteClienteRepository,
			EnderecoRepository enderecoRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository,
			ItemPedidoRepository itemPedidoRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
		this.clieteClienteRepository = clieteClienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.pedidoRepository = pedidoRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Urberlância", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jadim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clieteClienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
//		10:32 30/09/2017
		
		Pedido  ped1 = new Pedido(null, LocalDateTime.of(2017, 9, 30, 10, 32), cli1, e1);
		Pedido ped2 = new Pedido(null, LocalDateTime.of(2017, 10, 10, 19, 35), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); 
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, LocalDate.of(2017, 10, 20), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 1, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}

}
