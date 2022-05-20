package com.felixsilva.cursomc.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public Optional<Categoria> buscar(Integer id) {
		return categoriaRepository.findById(id);
	}

}
