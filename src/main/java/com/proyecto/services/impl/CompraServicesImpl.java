package com.proyecto.services.impl;

import java.util.List;
import java.util.Optional; // Asegúrate de tener este import!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importar Transactional

import com.proyecto.datos.CompraFiltros;
import com.proyecto.datos.RespuestaResultado;
import com.proyecto.models.Compra;
import com.proyecto.models.DetalleBoleta; // Importar DetalleBoleta
import com.proyecto.models.Boleta; // Importar Boleta, ya que se accede a ella desde DetalleBoleta
import com.proyecto.repository.CompraRepository;
import com.proyecto.repository.DetalleBoletaRepository; // Importar DetalleBoletaRepository
import com.proyecto.service.CompraService;

@Service
public class CompraServicesImpl implements CompraService {

	@Autowired
	private CompraRepository compraRepo;

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository; // Inyectar DetalleBoletaRepository

	@Override
	@Transactional(readOnly = true) // Añadir @Transactional para lectura
	public List<Compra> listarTodas() {
		List<Compra> compras = compraRepo.findAllByOrderByIdCompraDesc();

        // Iterar sobre cada compra para poblar la propiedad transitoria numeroBoletaAsociada.
        // Esta propiedad no se guarda en la base de datos, solo se usa para la vista.
        for (Compra compra : compras) {
            // Buscar los detalles de boleta asociados a esta compra específica.
            // Se asume que 'DetalleBoletaRepository' tiene un método como 'findByCompraIdCompra(String idCompra)'
            // que devuelve una lista de 'DetalleBoleta' para un 'idCompra' dado.
            List<DetalleBoleta> detalles = detalleBoletaRepository.findByCompraIdCompra(compra.getIdCompra());

            // Si se encuentran detalles de boleta para esta compra:
            if (!detalles.isEmpty()) {
                // Tomamos el 'numero_boleta' del primer detalle encontrado.
                // Accedemos al objeto Boleta dentro de DetalleBoleta y luego a su numeroBoleta.
                // Si una compra puede estar en múltiples boletas y necesitas una lógica diferente (ej. el más reciente),
                // deberás ajustar esta parte.
                Integer numeroBoleta = detalles.get(0).getBoleta().getNumeroBoleta(); // CORRECCIÓN AQUÍ
                compra.setNumeroBoletaAsociada(numeroBoleta); // Asigna el número de boleta a la propiedad transitoria
            }
        }
		return compras;
	}

	@Override
	@Transactional(readOnly = true) // Añadir @Transactional para lectura
	public List<Compra> busqueda(CompraFiltros compraFiltros) {
	    List<Compra> comprasFiltradas = compraRepo.findAllWithFilters(compraFiltros.getIdProveedor(), compraFiltros.getIdRopa(),
			 compraFiltros.getIdTalla(), compraFiltros.getIdColegio()) ;

        // También poblar numeroBoletaAsociada para las compras filtradas
        for (Compra compra : comprasFiltradas) {
            List<DetalleBoleta> detalles = detalleBoletaRepository.findByCompraIdCompra(compra.getIdCompra());
            if (!detalles.isEmpty()) {
                Integer numeroBoleta = detalles.get(0).getBoleta().getNumeroBoleta(); // CORRECCIÓN AQUÍ
                compra.setNumeroBoletaAsociada(numeroBoleta);
            }
        }
		return comprasFiltradas;
	}

	@Override
	@Transactional // Añadir @Transactional para escritura
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

    @Override // Añadir @Override
	@Transactional(readOnly = true) // Añadir @Transactional para lectura
	public Compra buscarPorId(String id) {
		Compra compra = compraRepo.findById(id).orElseThrow();
        // También poblar numeroBoletaAsociada si esta compra se va a mostrar en algún detalle
        List<DetalleBoleta> detalles = detalleBoletaRepository.findByCompraIdCompra(compra.getIdCompra());
        if (!detalles.isEmpty()) {
            Integer numeroBoleta = detalles.get(0).getBoleta().getNumeroBoleta(); // CORRECCIÓN AQUÍ
            compra.setNumeroBoletaAsociada(numeroBoleta);
        }
        return compra;
	}

	@Override
	@Transactional // Añadir @Transactional para escritura
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
	
    @Override // Añadir @Override
    @Transactional // Añadir @Transactional para escritura
    public RespuestaResultado cambiarEstado(String id) {

    	Compra compra = this.buscarPorId(id); // Este buscarPorId ya poblará numeroBoletaAsociada
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
