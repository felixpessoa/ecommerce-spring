package com.felixsilva.cursomc.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.repository.CategoriaRepository;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setCategoriaId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		findById(obj.getCategoriaId());
		return categoriaRepository.save(obj);
	}

}
