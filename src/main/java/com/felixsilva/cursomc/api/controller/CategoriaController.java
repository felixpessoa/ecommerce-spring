package com.felixsilva.cursomc.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
=======
import javax.validation.Valid;

>>>>>>> branch 'main' of https://github.com/felixpessoa/ecommerce-spring.git
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

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.service.CategoriaService;
import com.felixsilva.cursomc.dto.CategoriaDTO;

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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = categoriaService.fromDTO(objDto);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCategoriaId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{categoriaId}")
	public ResponseEntity<Void> update(@PathVariable Integer categoriaId,@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = categoriaService.fromDTO(objDto);
		obj.setCategoriaId(categoriaId);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<Categoria> delete(@PathVariable Integer categoriaId){
		categoriaService.delete(categoriaId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direcString){
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direcString);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
