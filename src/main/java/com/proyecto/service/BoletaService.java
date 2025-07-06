package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.datos.RespuestaResultado;
import com.proyecto.models.Boleta;
import com.proyecto.models.Compra;
import com.proyecto.models.DetalleBoleta;
import com.proyecto.models.Ropa;
import com.proyecto.repository.BoletaRepository;
import com.proyecto.repository.CompraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

	@Autowired
	private BoletaRepository _boletaRepository;

	@Autowired
	private CompraRepository compraRepository;

	public List<Boleta> search() {
		return _boletaRepository.findAllByOrderByNumeroBoletaDesc();
	}

	@Transactional
	public RespuestaResultado create(Boleta boleta) {
		try {
			StringBuilder errores = new StringBuilder();

			//Validamos si hay stock suficiente con cada item del detalle
			for (DetalleBoleta item : boleta.getListaBoleta()) {
				String idCompra = item.getCompra().getIdCompra();
				Compra com = compraRepository.findById(idCompra).orElseThrow();

				if (com.getCantidad() < item.getCantidad())
					errores.append(String.format("Stock insuficiente para %s <br>", com.getIdRopa().getNombreRopa()));
			}

			if (errores.length() > 0)
				return new RespuestaResultado(false, errores.toString());

			// Si todo OK, actualizamos stock de cada producto
			boleta.getListaBoleta().forEach(detalle -> {
				Compra com = compraRepository.findById(detalle.getCompra().getIdCompra()).orElseThrow();
				com.setCantidad(com.getCantidad() - detalle.getCantidad());
				compraRepository.save(com);
			});

			//Registramos la boleta
			Boleta registrado = _boletaRepository.save(boleta);
			String mensaje = String.format("Boleta con número %s registrada", registrado.getNumeroBoleta());
			return new RespuestaResultado(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new RespuestaResultado(false, "Error al registrar: " + ex.getMessage());
		}
	}
}
