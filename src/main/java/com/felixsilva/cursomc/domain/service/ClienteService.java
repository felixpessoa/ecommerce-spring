package com.felixsilva.cursomc.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felixsilva.cursomc.domain.model.Cidade;
import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.model.Endereco;
import com.felixsilva.cursomc.domain.model.enums.TipoCliente;
import com.felixsilva.cursomc.domain.repository.CidadeRepository;
import com.felixsilva.cursomc.domain.repository.ClienteRepository;
import com.felixsilva.cursomc.domain.repository.EnderecoRepository;
import com.felixsilva.cursomc.domain.service.exception.DataIntegrityException;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;
import com.felixsilva.cursomc.dto.ClienteDTO;
import com.felixsilva.cursomc.dto.ClienteNewDTO;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private CidadeRepository cidadeRepository;
	private EnderecoRepository enderecoRepository;

	public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.cidadeRepository = cidadeRepository;
		this.enderecoRepository = enderecoRepository;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setClienteId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getClienteId());
		updateData(newObj, obj);
		return clienteRepository.save(obj);
	}

	public void delete(Integer clienteId) {
		findById(clienteId);
		try {
			clienteRepository.deleteById(clienteId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getClienteId(), obj.getNome(), obj.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
