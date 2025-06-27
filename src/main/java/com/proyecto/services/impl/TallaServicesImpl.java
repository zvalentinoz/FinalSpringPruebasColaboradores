package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Talla;
import com.proyecto.repository.TallaRepository;
import com.proyecto.service.TallaService;
@Service
public class TallaServicesImpl implements TallaService {

	@Autowired
	private TallaRepository tallaRepo;
	
	@Override
	public List<Talla> listarTodas() {
		
		return tallaRepo.findAll();
	}

}
