package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Colegio;
import com.proyecto.repository.ColegioRepository;
import com.proyecto.service.ColegioService;
@Service
public class ColegioServiceImpl implements ColegioService {

	@Autowired
	private ColegioRepository colegioRepo;
	
	@Override
	public List<Colegio> listarTodos() {
		 
		return colegioRepo.findAll() ;
	}

}
