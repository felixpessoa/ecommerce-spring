package com.felixsilva.cursomc.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felixsilva.cursomc.domain.model.Categoria;
import com.felixsilva.cursomc.domain.repository.CategoriaRepository;
import com.felixsilva.cursomc.domain.service.exception.DataIntegrityException;
import com.felixsilva.cursomc.domain.service.exception.ObjectNotFoundException;
import com.felixsilva.cursomc.dto.CategoriaDTO;

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
	
	public void delete(Integer categoriaId) {
		findById(categoriaId);
		try {
			categoriaRepository.deleteById(categoriaId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getCategoriaId(), objDto.getNome());
	}

}
