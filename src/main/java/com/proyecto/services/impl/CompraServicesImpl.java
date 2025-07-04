package com.proyecto.services.impl;

import java.util.List;
import java.util.Optional; // ¡Asegúrate de tener este import!

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
	
   /* @Override
    public RespuestaResultado cambiarEstado(String id) {
        Optional<Compra> optionalCompra = compraRepo.findById(id);
        if (optionalCompra.isPresent()) {
            Compra compra = optionalCompra.get();
            // Invierte el estado actual: si es true, lo pone en false; si es false, lo pone en true
            compra.setEstado(!compra.isEstado());
            try {
                Compra registro = compraRepo.save(compra); // Guarda el cambio de estado
                String nuevoEstado = registro.isEstado() ? "Activo" : "Inactivo";
                String msg = String.format("Estado de la compra con código %s cambiado a %s", registro.getIdCompra(), nuevoEstado);
                return new RespuestaResultado(true, msg);
            } catch (Exception e) {
                e.printStackTrace();
                return new RespuestaResultado(false, "Error al cambiar el estado de la compra: " + e.getMessage());
            }
        } else {
            return new RespuestaResultado(false, "Compra con ID " + id + " no encontrada.");
        }
    }
    */
    
    public RespuestaResultado cambiarEstado(String id) {

    	Compra compra = this.buscarPorId(id);
		String accion = compra.getEstado() ? "desactivado" : "activado";

		compra.setEstado(!compra.getEstado());

		try {
			Compra registrado = compraRepo.save(compra);

			String mensaje = String.format("Producto con código %s %s", registrado.getIdCompra(), accion);
			return new RespuestaResultado(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new RespuestaResultado(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}
    
    public List<Compra> getActivos() {
		return compraRepo.findAllByEstado(true);
	}
}