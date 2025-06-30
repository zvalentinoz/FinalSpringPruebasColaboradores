package com.proyecto.service;

import java.util.List;


import com.proyecto.models.Producto;


public interface ProductoService {
 List<Producto> listarTodos();
 void registrar(Producto producto);
}
