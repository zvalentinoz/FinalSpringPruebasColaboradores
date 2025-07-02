package com.proyecto.service;

import java.util.List;

import com.proyecto.datos.CompraFiltros;
import com.proyecto.datos.RespuestaResultado;
import com.proyecto.models.Compra;


public interface CompraService {

	List<Compra> listarTodas();
	List<Compra> busqueda(CompraFiltros compraFiltros);
	RespuestaResultado  registrarCompra(Compra compra);
	Compra buscarPorId(String id);
	RespuestaResultado ActualizarCompra(Compra compra);
}
