package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Transaccion;
import com.proyecto.repository.TransaccionRepository;
import com.proyecto.service.TransaccionService;
@Service
public class TransaccionServicesImpl implements TransaccionService {

	@Autowired
	private TransaccionRepository transaccionRepo;
	
	@Override
	public List<Transaccion> listarTodas() {
	 
		return transaccionRepo.findAll();
	}

	@Override
	public Transaccion registrarVenta(Transaccion transaccion) {
		
		return transaccionRepo.save(transaccion) ;
	}

	@Override
	public void eliminarVenta(Integer id) {
		transaccionRepo.deleteById(id);
		
	}

	@Override
	public Transaccion buscarPorId(Integer id) {
		return  transaccionRepo.findById(id).orElseThrow();

	}

}
