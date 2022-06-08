package com.felixsilva.cursomc.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.repository.ClienteRepository;
import com.felixsilva.cursomc.domain.service.exception.DataIntegrityException;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;
import com.felixsilva.cursomc.dto.ClienteDTO;

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
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getClienteId(), obj.getNome(), obj.getEmail(), null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
