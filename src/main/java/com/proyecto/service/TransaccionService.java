package com.proyecto.service;

import java.util.List;


import com.proyecto.models.Transaccion;


public interface TransaccionService {

	List<Transaccion> listarTodas();
	Transaccion registrarVenta(Transaccion transaccion);
	void eliminarVenta(Integer id);
	Transaccion buscarPorId(Integer id);
}
