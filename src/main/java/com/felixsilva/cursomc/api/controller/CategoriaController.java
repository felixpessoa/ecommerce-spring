package com.felixsilva.cursomc.api.controller;

import java.net.URI;

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

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}
	
	
	@GetMapping("/{categoriaId}")
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable Integer categoriaId){
		Categoria obj = categoriaService.findById(categoriaId);
		return ResponseEntity.status(HttpStatus.OK).body(obj); 
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCategoriaId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	
	
	
}
