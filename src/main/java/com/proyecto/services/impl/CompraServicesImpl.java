package com.proyecto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.datos.CompraFiltros;
import com.proyecto.datos.RespuestaResultado;
import com.proyecto.models.Compra;
import com.proyecto.repository.CompraRepository;
import com.proyecto.service.CompraService;
@Service
public class CompraServicesImpl implements CompraService {

	@Autowired
	private CompraRepository compraRepo;

	@Override
	public List<Compra> listarTodas() {
		 
		return compraRepo.findAllByOrderByIdCompraDesc();
	}

	@Override
	public List<Compra> busqueda(CompraFiltros compraFiltros) {
	    
		return compraRepo.findAllWithFilters(compraFiltros.getIdProveedor(), compraFiltros.getIdRopa(),
			 compraFiltros.getIdTalla(), compraFiltros.getIdColegio()) ;
	}

	@Override
	public RespuestaResultado registrarCompra(Compra compra) {
		
		try {
			 Compra registro = compraRepo.save(compra);
			 
			 String msg = String.format("Compra con el codigo %s  registrado", registro.getIdCompra());
			 return new RespuestaResultado(true, msg);
		} catch (Exception e) {
			e.printStackTrace();
			return new RespuestaResultado(false,"Error al registrar la compra: " + e.getMessage());
		}
		
	}

	@Override
	public Compra buscarPorId(String id) {
		return compraRepo.findById(id).orElseThrow();
	}

	@Override
	public RespuestaResultado ActualizarCompra(Compra compra) {
		 
		try {
			Compra registro = compraRepo.save(compra);
			 
			 String msg = String.format("Compra con el codigo %s Actualizado", registro.getIdCompra());
			 return new RespuestaResultado(true, msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new RespuestaResultado(false, "ERROR AL ACTUALIZAR LA COMPRA: " + e.getMessage());
		}
		
		
	}
	
	
	
	

}
