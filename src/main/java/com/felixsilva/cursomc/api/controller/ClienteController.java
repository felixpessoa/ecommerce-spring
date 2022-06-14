package com.felixsilva.cursomc.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felixsilva.cursomc.domain.model.Cliente;
import com.felixsilva.cursomc.domain.service.ClienteService;
import com.felixsilva.cursomc.dto.ClienteDTO;
import com.felixsilva.cursomc.dto.ClienteNewDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> findByIdCliente(@PathVariable Integer clienteId) {
		Cliente obj = clienteService.findById(clienteId);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getClienteId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Void> update(@PathVariable Integer clienteId, @Valid @RequestBody ClienteDTO objDto) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj.setClienteId(clienteId);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer clienteId) {
		clienteService.delete(clienteId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcString) {
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direcString);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
