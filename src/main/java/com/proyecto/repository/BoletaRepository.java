package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Boleta;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
	List<Boleta> findAllByOrderByNumeroBoletaDesc();

}
