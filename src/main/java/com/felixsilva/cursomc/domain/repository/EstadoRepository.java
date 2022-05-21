package com.felixsilva.cursomc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felixsilva.cursomc.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
