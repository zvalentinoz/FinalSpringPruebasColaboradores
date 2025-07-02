package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Ropa;
import com.proyecto.repository.ProveedorRepository;
import com.proyecto.repository.RopaRepository;
import com.proyecto.service.RopaService;
@Service
public class RopaServiceImpl implements RopaService {

	@Autowired
	 private RopaRepository ropaRepo;
	
	@Override
	public List<Ropa> listarTodos() {
		
		return ropaRepo.findAll();
	}




}
