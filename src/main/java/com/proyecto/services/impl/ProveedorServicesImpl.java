package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Proveedor;
import com.proyecto.repository.ProveedorRepository;
import com.proyecto.service.ProveedorService;

@Service
public class ProveedorServicesImpl implements ProveedorService {
   
	 @Autowired 
	  ProveedorRepository proveedorRepository; 
	 
	@Override
	public List<Proveedor> listarTodo() {
		
		return  proveedorRepository.findAll();
	}

}
