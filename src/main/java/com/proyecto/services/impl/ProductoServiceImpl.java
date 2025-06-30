package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Producto;
import com.proyecto.repository.ProductoRepository;
import com.proyecto.service.ProductoService;
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	 private ProductoRepository productoRepo;
	
	@Override
	public List<Producto> listarTodos() {
		
		return productoRepo.findAll();
	}

	@Override
	public void registrar(Producto producto) {
			 productoRepo.save(producto);
		
	}

}
